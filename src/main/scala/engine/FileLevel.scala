package engine

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import process.Encryption

object FileLevel {

  def encryptFile(p: String, algo: Encryption, key: String): Boolean = {
    applyFuncToFile(p, encryptFunc(algo)(key))
  }

  def decryptFile(p: String, algo: Encryption, key: String): Boolean = {
    applyFuncToFile(p, decryptFunc(algo)(key))
  }

  private def decryptFunc(algo: Encryption)(key: String)(arr: Array[Byte]): Array[Byte] = algo.decrypt(key, arr)

  private def encryptFunc(algo: Encryption)(key: String)(arr: Array[Byte]): Array[Byte] = algo.encrypt(key, arr)

  private def applyFuncToFile(p: String, algo: Array[Byte] => Array[Byte]) = {
    val hdfs = FileSystem.get(new Configuration())
    val pathIn = new Path(p)
    val pathOut = new Path(s"{p}_tmp")
    var buffer = new Array[Byte](1024)
    val in = hdfs.open(pathIn)
    val out = hdfs.create(pathOut)

    var numBytes = in.read(buffer)
    while (numBytes > 0) {
      val resultArr = algo(buffer)
      out.write(resultArr, 0, numBytes)
      numBytes = in.read(buffer)
    }

    in.close()
    out.close()

    hdfs.rename(pathOut, pathIn)
  }
}
