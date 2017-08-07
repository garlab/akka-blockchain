package blockchain.actors

import akka.actor.ActorSystem
import akka.actor.Props
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import blockchain.entities.Block

class MiningActorSpec extends TestKit(ActorSystem()) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  import MiningActor._

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A mining actor" must {

    "mine a given block" in {
      val miningActor = system.actorOf(Props[MiningActor], "Mining")
      val block = Block(1, 0, 1502057177, "hello", "a37421b94e3c053a16abb234d7fcf1dc85c995d9ba1d8d12aee9cbfad9fa5836")
      val minedBlock = block.withNonce(4957)

      miningActor ! Mine(block)
      expectMsg(minedBlock)
    }

  }
}
