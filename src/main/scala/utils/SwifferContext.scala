package utils

import java.io.File

import com.typesafe.config.ConfigFactory
import configs.Configs
import configs.syntax._
import process.Encryption

object SwifferContext {

  case class ColumnProcessing(column: String, process: Encryption)
  case class SwifferContext(columns: List[ColumnProcessing])

  val config = ConfigFactory.parseFile(new File(""))

  val swifferConfig = config.get[SwifferContext]("")

}
