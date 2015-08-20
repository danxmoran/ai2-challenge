/**
 * Class responsible for producing anagrams of strings
 */
class Anagrammer(dictionary: Set[String]) {

  /**
   * Computing all anagrams for a given word has horrible asymptotic efficiency,
   * especially when most of the anagrams will end up being filtered out by the
   * dictionary check.
   *
   * Instead of doing that, use the fact that if two strings are anagrams
   * they contain the exact same set of characters, and group the dictionary
   * words according to their character sets for later access.
   */
  val anagramCache = dictionary.groupBy[Set[Char]] { _.toSet[Char] }

  /**
   * Get all valid anagrams for a given word.
   *
   * @param word The word to compute anagrams from.
   * @return All anagrams of the given word that exist in this object's dictionary.
   */
  def getAnagrams(word: String): Set[String] =
    this.anagramCache.getOrElse(word.toSet[Char], { Set[String]() })

}
