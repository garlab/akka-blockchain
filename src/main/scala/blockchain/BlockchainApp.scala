package blockchain

import akka.actor._
import blockchain.actors.BlockchainActor
import blockchain.actors.BlockchainActor._

object BlockchainApp extends App {
  val system = ActorSystem("akka-blockchain")
  val blockchain = system.actorOf(Props[BlockchainActor], "BlockchainActor")

  blockchain ! AddBlock("fooobar")
  blockchain ! AddBlock("hello")

  Thread.sleep(5000)
  system.terminate()
}
