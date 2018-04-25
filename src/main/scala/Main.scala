import utils.SwifferArgParser
import utils.SwifferArgParser.ConfigArg

object Main {
  def main(args: Array[String]): Unit = {

    SwifferArgParser.parser.parse(args, ConfigArg()) match {
      case Some(config) =>
        if(config.fileLevel) {
          // Cipher the entire file
        }
        else {
          // Look for the context and cipher only some columns
        }
      case None =>
      // arguments are bad, error message will have been displayed
    }

  }
}
