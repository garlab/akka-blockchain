package blockchain.actors

import akka.actor.ActorSystem
import akka.actor.Props
import akka.testkit.{ImplicitSender, TestActorRef, TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import blockchain.entities.Block
import blockchain.actors.MiningActor._

class BlockchainActorSpec extends TestKit(ActorSystem()) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  import BlockchainActor._

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A blockchain actor" must {

    "add a given block" in {
      val miningProbe = TestProbe()
      val blockchainActor = TestActorRef(Props(new BlockchainActor {
        override val miningActor = miningProbe.ref
      }))

      val minedBlock = Block(1, 4957, 1502057177, "hello", "a37421b94e3c053a16abb234d7fcf1dc85c995d9ba1d8d12aee9cbfad9fa5836")

      blockchainActor ! AddBlock("hello")
      miningProbe.send(blockchainActor, minedBlock)
      miningProbe.expectMsgClass(classOf[Mine])

      //expectMsg(BlockSaved(minedBlock))
    }

  }
}
