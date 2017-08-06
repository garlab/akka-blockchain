package blockchain

import Crypto._

case class Block(index: Int, nonce: Long, timestamp: Long, data: String, previousHash: String) {
  def hash = sha256Hash(
    index.toString + nonce.toString + timestamp.toString + data + previousHash
  )

  def withNonce(nonce: Long) = copy(nonce = nonce)
  
  def satisfiesPow = hash startsWith "0000"
}
