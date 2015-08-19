import org.scalatest._

class AnagrammerSpec extends FlatSpec with Matchers {

  "An Anagrammer" should "properly compute string anagrams" in {
    val anagrams = List("cat", "cta", "act", "atc", "tac", "tca")
    val anagrammer = new Anagrammer(anagrams.toSet[String])

    val catAnagrams = anagrammer.getAnagrams("cat")
    catAnagrams.length shouldEqual anagrams.length
    catAnagrams.foreach { anagram =>
      anagrams should contain (anagram)
    }
  }

  it should "return no anagrams if initialized with an empty dictionary" in {
    val anagrammer = new Anagrammer(Set[String]())
    anagrammer.getAnagrams("apple") should be (List[String]())
    anagrammer.getAnagrams("orange") should be (List[String]())
  }

  it should "only return valid anagrams if initialized with a non-empty dictionary" in {
    val anagrammer1 = new Anagrammer(Set("stab", "tabs"))
    val batsAnagrams1 = anagrammer1.getAnagrams("bats")
    batsAnagrams1.length should be (2)
    batsAnagrams1 should contain ("stab")
    batsAnagrams1 should contain ("tabs")

    val anagrammer2 = new Anagrammer(Set("stab"))
    val batsAnagrams2 = anagrammer2.getAnagrams("bats")
    batsAnagrams2.length should be (1)
    batsAnagrams2 should contain ("stab")
  }

}
