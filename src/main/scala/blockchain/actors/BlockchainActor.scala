package blockchain.actors

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props
import akka.pattern.{ask, pipe}
import akka.util.Timeout
import concurrent._
import concurrent.duration._
import concurrent.ExecutionContext.Implicits.global

import blockchain.entities.Block
import blockchain.actors.MiningActor._

object BlockchainActor {
  case object RequestChain
  case class AddBlock(data: String)
  case class BlockSaved(block: Block)
  case class BlockRejected(block: Block)
}

class BlockchainActor extends Actor {
  import BlockchainActor._

  private var blocks: List[Block] = Nil

  val miningActor = context.actorOf(Props[MiningActor], "Mining")

  def timestamp: Long = System.currentTimeMillis / 1000

  override def preStart() {
    blocks ::= Block(0, 0, timestamp, "Genesis block", "0" * 64)
  }

  def receive = {
    case AddBlock(data) =>
      val block = Block(blocks.head.index + 1, 0, timestamp, data, blocks.head.hash)
      val replyTo = sender
      implicit val timeout = Timeout(4 seconds)

      println(s"Block mined ${blocks.head.index} $data")

      ((miningActor ? Mine(block)) map (replyTo -> _)) pipeTo self

    case (replyTo: ActorRef, block: Block) =>
      if (block.index == blocks.head.index + 1) {
        blocks = block :: blocks
        println("Block saved")
        replyTo ! BlockSaved(block)
      } else {
        println("Block rejected")
        replyTo ! BlockRejected(block)
      }
      println(s"Block #${block.index}, nonce: ${block.nonce}, hash: ${block.hash}")
  }
}
