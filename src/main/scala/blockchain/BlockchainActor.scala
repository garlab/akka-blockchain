package blockchain

import akka.actor.Actor

object BlockchainActor {
  case object RequestChain
  case class AddBlock(data: String)
}

class BlockchainActor extends Actor {
  private var blocks: List[Block] = Nil

  def timestamp: Long = System.currentTimeMillis / 1000

  override def preStart() {
    blocks ::= Block(0, 0, timestamp, "Genesis block", "0" * 64)
  }

  def receive = {
    case AddBlock(data) =>
      val block = Block(blocks.head.index + 1, 0, timestamp, data, blocks.head.hash)
      blocks = block :: blocks
      println(s"Block ${blocks.head.index} $data")
      println(s"Hash: ${blocks.head.hash}")
  }
}
