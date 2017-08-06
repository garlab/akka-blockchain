package blockchain

import akka.actor.Actor

case class AddBlock(data: String)

class BlockchainActor extends Actor {
  private var blocks: List[Block] = Nil

  def timestamp: Long = System.currentTimeMillis / 1000

  override def preStart() {
    blocks = Block(0, timestamp, "Genesis block", "0") :: blocks
  }

  def receive = {
    case AddBlock(data) =>
      val block = Block(blocks.head.index + 1, timestamp, data, blocks.head.hash)
      blocks = block :: blocks
      println(s"Block ${blocks.head.index} $data")
      println(s"Hash: ${blocks.head.hash}")
  }
}
