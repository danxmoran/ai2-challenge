/**
 * Class responsible for producing anagrams of strings
 */
class Anagrammer(val dictionary: Set[String]) {

  /**
   * Cache for previously-computed anagrams, to avoid repeated computation.
   */
  var anagramCache = Map[List[Char], List[String]]()

  /**
   * Get all valid anagrams for a given word.
   *
   * @param word The word to compute anagrams from.
   * @return All anagrams of the given word that exist in this object's dictionary.
   */
  def getAnagrams(word: String): List[String] = {

    val chars = word.toList.sorted
    this.anagramCache.getOrElse(chars, {
      val anagrams = chars.permutations
        .map(_.mkString)
        .filter(this.dictionary.contains(_))
        .toList
      this.anagramCache += chars -> anagrams
      anagrams
    })
  }

}
