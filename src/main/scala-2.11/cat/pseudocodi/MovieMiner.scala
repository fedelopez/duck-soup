package cat.pseudocodi

/**
  * @author fede
  */
object MovieMiner {

  def imdbTop250MovieTagLines() : List[(String, String)] = {
    val movies = IMDbCrawler.topRatedMovies()
    val idToTitle = movies.map(title => (MovieDB.movieIdByTitle(title), title))
    idToTitle.filter(_._1 > -1).map((tuple: (Int, String)) => (tuple._2, MovieDB.tagline(tuple._1.toString)))
  }

}
