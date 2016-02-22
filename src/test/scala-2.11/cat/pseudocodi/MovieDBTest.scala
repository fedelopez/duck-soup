package cat.pseudocodi

import org.scalatest._

/**
  * @author fede
  */
class MovieDBTest extends FlatSpec with Matchers {

  "movies by title" should "return matching movies" in {
    val id: Int = MovieDB.movieIdByTitle("The Shawshank Redemption")
    assert(id === 278)
  }

  "movie by code" should "return matching movie" in {
    val json: String = MovieDB.movieByCode("76341")
    println(json)
  }

  "movie tagline" should "return tagline" in {
    val tagline: String = MovieDB.tagline("76341")
    assert(tagline === "What a Lovely Day.")
  }

  "similar movies" should "return similar movies to movie id" in {
    val json: String = MovieDB.similarMovies("76341")
    println(json)
  }

  "top rated movies" should "return list of top rated movies with 50 votes or more" in {
    val json: String = MovieDB.topRatedMovies()
    println(json)
  }

  "genres" should "return all genres" in {
    val genres: List[String] = MovieDB.genres()
    println(s"${genres.size} genres found")
    genres.foreach(println)
  }

  "keywords" should "return a list of matching keywords" in {
    val list: List[String] = MovieDB.keywords("tarantino")
    println(s"${list.size} keywords found")
    list.foreach(println)
  }

  "popular people" should "return a list of popular people" in {
    val list: List[String] = MovieDB.popularPeople()
    println(s"${list.size} people found")
    list.foreach(println)
  }
}
