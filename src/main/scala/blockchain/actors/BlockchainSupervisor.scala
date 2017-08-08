import akka.actor.{Actor, ActorLogging, Props}

object BlockchainSupervisor {
  def props(): Props = Props(new BlockchainSupervisor())
}

class BlockchainSupervisor extends Actor with ActorLogging {
  override def preStart(): Unit = log.info("Blockchain Application started")
  override def postStop(): Unit = log.info("Blockchain Application stopped")

  // No need to handle any messages
  override def receive = Actor.emptyBehavior

}
