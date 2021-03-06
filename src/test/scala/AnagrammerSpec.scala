import org.scalatest._

class AnagrammerSpec extends FlatSpec with Matchers {

  "An Anagrammer" should "properly compute string anagrams" in {
    val anagrams = Set("cat", "cta", "act", "atc", "tac", "tca")
    val anagrammer = new Anagrammer(anagrams.toSet[String])

    val catAnagrams = anagrammer.getAnagrams("cat")
    catAnagrams.size shouldEqual anagrams.size
    catAnagrams.foreach { anagram =>
      anagrams should contain (anagram)
    }
  }

  it should "return no anagrams if initialized with an empty dictionary" in {
    val anagrammer = new Anagrammer(Set[String]())
    anagrammer.getAnagrams("apple") should have size 0
    anagrammer.getAnagrams("orange") should have size 0
  }

  it should "only return valid anagrams if initialized with a non-empty dictionary" in {
    val anagrammer1 = new Anagrammer(Set("stab", "tabs"))
    val batsAnagrams1 = anagrammer1.getAnagrams("bats")
    batsAnagrams1.size should be (2)
    batsAnagrams1 should contain ("stab")
    batsAnagrams1 should contain ("tabs")

    val anagrammer2 = new Anagrammer(Set("stab"))
    val batsAnagrams2 = anagrammer2.getAnagrams("bats")
    batsAnagrams2.size should be (1)
    batsAnagrams2 should contain ("stab")
  }

  it should "keep track of duplicate characters when computing anagrams" in {
    val anagrammer = new Anagrammer(Set("banana"))
    val banAnagrams = anagrammer.getAnagrams("ban")
    banAnagrams should have size 0
  }

}
