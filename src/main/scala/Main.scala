import utils.SwifferArgParser
import utils.SwifferArgParser.ConfigArg

object Main {
  def main(args: Array[String]): Unit = {

    SwifferArgParser.parser.parse(args, ConfigArg()) match {
      case Some(config) =>
      // do stuff

      case None =>
      // arguments are bad, error message will have been displayed
    }

  }
}
