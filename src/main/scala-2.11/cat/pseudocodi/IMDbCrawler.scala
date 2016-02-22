package cat.pseudocodi

import org.jsoup.Jsoup

import scala.collection.JavaConversions._

/**
  * @author fede
  */
object IMDbCrawler {

  def topRatedMovies(): List[String] = {
    val doc = Jsoup.connect("http://www.imdb.com/chart/top?ref_=nv_mv_250_6").get()
    val titleColumns = doc.getElementsByClass("titleColumn")
    titleColumns.iterator().map(elem => elem.getElementsByTag("a").text).toList
  }

  def topRatedTVShows(): List[String] = {
    val doc = Jsoup.connect("http://www.imdb.com/chart/toptv/?ref_=nv_tp_tv250_2").get()
    val titleColumns = doc.getElementsByClass("titleColumn")
    titleColumns.iterator().map(elem => elem.getElementsByTag("a").text).toList
  }

}
