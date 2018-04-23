package utils

object SwifferArgParser {

  case class ConfigArg(fileLevel: Boolean = true, key: String = "my private key")

  val parser = new scopt.OptionParser[ConfigArg]("swiffer"){
    head("swiffer", "0.1")

    opt[Boolean]('l', "file-level")
      .required()
      .action((x, c) => c.copy(fileLevel = x))
      .text("True to cipher the entire file, False to cipher only several columns")

    opt[String]('k', "key")
      .required()
      .action((x, c) => c.copy(key = x))
      .text("Key use to encrypt or decrypt your file/data")

    help("help").text("print this usage text")
  }
}
