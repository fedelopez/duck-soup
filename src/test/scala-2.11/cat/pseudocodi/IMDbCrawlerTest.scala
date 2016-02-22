package cat.pseudocodi

import org.scalatest._

/**
  * @author fede
  */
class IMDbCrawlerTest extends FlatSpec with Matchers {

  "topRatedMovies" should "return 250 movie titles" in {
    val movies = IMDbCrawler.topRatedMovies()
    assert(movies.length === 250)
    assert(movies.head === "The Shawshank Redemption")
  }

  "topRatedTVShows" should "return 250 tv shows" in {
    val tvShows = IMDbCrawler.topRatedTVShows()
    assert(tvShows.length === 250)
    assert(tvShows.head === "Band of Brothers")
  }
}


