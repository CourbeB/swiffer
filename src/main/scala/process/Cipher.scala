package process

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import org.apache.commons.codec.binary.Base64

trait Encryption {
  def encrypt(key: String, toEncrypt: String): String
  def decrypt(key: String, toEncrypt: String): String
  def encrypt(key: String, toEncrypt: Array[Byte]): Array[Byte]
  def decrypt(key: String, toEncrypt: Array[Byte]): Array[Byte]
}

class JavaCryptoEncryption(algorithm: String) extends Encryption {
  private def decodeBase64(string: String) = Base64.decodeBase64(string)
  private def cipher(mode: Int, b64secret: String): Cipher = {
    val encipher = Cipher.getInstance(algorithm + "/ECB/PKCS5Padding")
    encipher.init(mode, new SecretKeySpec(decodeBase64(b64secret), algorithm))
    encipher
  }

  def encrypt(b64secret: String, toEncrypt: String): String = {
    val encoder = cipher(Cipher.ENCRYPT_MODE, b64secret)
    Base64.encodeBase64String(encoder.doFinal(toEncrypt.getBytes("UTF-8")))
  }

  def decrypt(b64secret: String, toDecrypt: String): String = {
    val decoder = cipher(Cipher.DECRYPT_MODE, b64secret)
    new String(decoder.doFinal(Base64.decodeBase64(toDecrypt)))
  }

  def encrypt(b64secret: String, bytes: Array[Byte]): Array[Byte] = {
    val encoder = cipher(Cipher.ENCRYPT_MODE, b64secret)
    encoder.doFinal(bytes)
  }

  def decrypt(b64secret: String, bytes: Array[Byte]): Array[Byte] = {
    val decoder = cipher(Cipher.DECRYPT_MODE, b64secret)
    decoder.doFinal(bytes)
  }
}

object AES extends JavaCryptoEncryption("AES")
object DES extends JavaCryptoEncryption("DES")