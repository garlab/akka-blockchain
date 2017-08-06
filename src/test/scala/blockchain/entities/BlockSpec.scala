package blockchain.entities

import org.scalatest._

class BlockSpec extends FlatSpec with Matchers {
  val block = Block(0, 42, 1502056914, "Some data", "0" * 64)
  val minedBlock = Block(1, 4957, 1502057177, "hello", "a37421b94e3c053a16abb234d7fcf1dc85c995d9ba1d8d12aee9cbfad9fa5836")

  it should "hash to the correct value" in {
    block.hash shouldEqual "b032d096fda23a63bd331a350d23dc603245664e1f9da025a3e3b5e61ef6ead7"
  }

  it should "say if block satisfies POW" in {
    block.satisfiesPow shouldEqual false
    minedBlock.satisfiesPow shouldEqual true
  }
}
