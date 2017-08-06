package blockchain

import akka.actor._

object Hello extends App {
  val system = ActorSystem("akka-blockchain")
  val blockchain = system.actorOf(Props[BlockchainActor])

  blockchain ! AddBlock("fooobar")
  blockchain ! AddBlock("hello")

  Thread.sleep(5000)
  system.terminate()
}
