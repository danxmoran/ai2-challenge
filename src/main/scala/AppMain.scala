import java.io.File
import scala.io.Source
import scopt.{OptionParser, Read}
import scala.util.{Try, Success, Failure}

/**
 * Main entry point to application.
 */
object AppMain {

  def main(args: Array[String]): Unit = {

    /**
     * Override scopt's basic file reading to check that file actually exists.
     */
    implicit val fileRead: Read[File] = Read.reads { filename =>
      val maybeFile = new File(filename)
      if (maybeFile.exists)
        maybeFile
      else
        throw new IllegalArgumentException(s"'$filename' is not a valid file")
    }

    /**
     * Arguments passed to application.
     */
    case class Args(words: Seq[String] = Seq(), dictFile: File = new File("."))

    val argParser = new OptionParser[Args]("ai2-challenge") {
      head("AI2-challenge: The Anagrammer")

      arg[String]("<word1> <word2> ...") unbounded() action { (word, args) =>
        args.copy(words = args.words :+ word)
      } text("words from which anagrams should be derived")

      opt[File]('d', "dictionary") valueName("<filename>") required() action { (filename, args) =>
        args.copy(dictFile = filename)
      } text("file containing valid anagrams, one per line")

      help("help") text("prints this usage text")
    }

    argParser.parse(args, Args()).foreach { arguments =>
      Try(this.parseDictionary(arguments.dictFile)) match {
        case Success(set) => {
          val anagrammer = new Anagrammer(set)
          arguments.words.foreach { word =>
            println(s"WORD: '$word'")
            val anagrams = anagrammer.getAnagrams(word)
            if (anagrams.nonEmpty) {
              println("ANAGRAMS:")
              anagrams foreach println
            } else {
              println("NO VALID ANAGRAMS")
            }
            println()
          }
        }
        case Failure(e) => {
          println(s"ERROR: Unable to parse dictionary file '${arguments.dictFile}'")
          println(s"       Parsing failed with ${e.toString}")
          System.exit(1)
        }
      }
    }
  }

  /**
   * Parse dictionary file to produce set of valid anagrams.
   * @param dictFile Object pointing to file that defines valid anagrams.
   * @return A set of strings containing all anagrams defined in the dictionary file.
   */
  def parseDictionary(dictFile: File): Set[String] =
    Source.fromFile(dictFile).getLines().toSet[String]

}
