package blockchain.utils

import java.math.BigInteger
import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object Crypto {
  def sha256Hash(text: String): String =
    String.format(
      "%064x",
      new BigInteger(
        1,
        MessageDigest.getInstance("SHA-256").digest(text.getBytes("UTF-8")
      )
    )
  )

  def generateHMAC(sharedSecret: String, preHashString: String): String = {
    //Crypto Funs : 'SHA256' , 'HmacSHA1'
    val secret = new SecretKeySpec(sharedSecret.getBytes, "SHA256")
    val mac = Mac.getInstance("SHA256")
    mac.init(secret)
    val hashString: Array[Byte] = mac.doFinal(preHashString.getBytes)
    new String(hashString.map(_.toChar))
  }
}
