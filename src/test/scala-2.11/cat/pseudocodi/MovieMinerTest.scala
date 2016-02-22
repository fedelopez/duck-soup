package cat.pseudocodi

import org.scalatest.{FlatSpec, Matchers}

/**
  * @author fede
  */
class MovieMinerTest extends FlatSpec with Matchers {

  "imdb top 250 movie tag lines" should "return a list of title to tagline" in {
    val actual: List[(String, String)] = MovieMiner.imdbTop250MovieTagLines()
    assert(actual.head === ("The Shawshank Redemption", "Fear can hold you prisoner. Hope can set you free."))
    val noTagline = actual.filter((tuple: (String, String)) => tuple._2 == null || tuple._2.isEmpty)
    println(s"${noTagline.length} movies with no tagline:")
    noTagline.foreach((tuple: (String, String)) => println(tuple._1))
  }

}
