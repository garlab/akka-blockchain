package blockchain.actors

import annotation.tailrec
import akka.actor.Actor
import blockchain.entities.Block

object MiningActor {
  case class Mine(block: Block)
}

class MiningActor extends Actor {
  import MiningActor._

  @tailrec
  private def mine(block: Block): Block = {
    if (block.satisfiesPow) {
      block
    } else {
      mine(block.withNonce(block.nonce + 1))
    }
  }

  def receive = {
    case Mine(block) =>
      sender ! mine(block)
  }
}
