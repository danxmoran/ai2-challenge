AI2 Challenge
=============

This project contains my solution to a challenge presented during the interview
process for the Allen Institute for Artificial Intelligence (AI2). The challenge
was as follows:
> You are given a dictionary file, with one word per line. Write a
> program that takes as input one or more words, and for each input
> word, print out all anagrams of the input word that appear in the
> dictionary. An anagram of a word is any word you can form by permuting
> the letters of the input word. For example, the word "stab" is an
> anagram of "bats", as is "tabs". Please include documentation for
> running your program. Try to make it easy-to-use and efficiently
> implemented.

Running the solution
--------------------

The project is managed and build using sbt. Instructions on downloading and
installing sbt can be found here: http://www.scala-sbt.org/download.html

Once sbt is up and running, clone / unzip the solution onto your machine and
build it by moving into its directory and running:

```bash
$ sbt one-jar
```

This will generate a standalone JAR file ```anagram.jar``` in
```target/scala-2.11``` containing the solution. __NOTE:__ if you're building
using a fresh sbt, this will take some time and you'll see a lot of log
output.

After the solution is built, run it with:

```bash
$ java -jar target/scala-2.11/anagram.jar -d <filename> <word1> <word2> ...

  <word1> <word2> ...
        words from which anagrams should be derived
  -d <filename> | --dictionary <filename>
        file containing valid anagrams, one per line
```
