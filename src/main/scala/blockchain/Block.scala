package blockchain

import Utils._

case class Block(index: BigInt, timestamp: Long, data: String, previousHash: String) {
  def hash = sha256Hash(
    index.toString + timestamp.toString + data + previousHash
  )
}
