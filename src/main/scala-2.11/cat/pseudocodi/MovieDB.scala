package cat.pseudocodi

import java.net.URLEncoder

/**
  * @author fede
  */
object MovieDB {

  val apiKey = System.getenv("TMDB_API_KEY")

  import dispatch._
  import Defaults._
  import org.json4s._
  import org.json4s.native.JsonMethods._

  def main(args: Array[String]) {
    val spaceship = "164023"
    val json = moviesByKeyword(spaceship)
    val keywordsRaw = compact(render(parse(json) \ "results" \ "genre_ids"))
    val num = """(\d+)""".r
    val keywordIds = num.findAllIn(keywordsRaw).map(s => s.toInt).toList.distinct
    keywordIds.foreach(println)
  }

  def moviesByKeyword(keyword: String): String = {
    val svc = url(s"https://api.themoviedb.org/3/keyword/$keyword/movies?api_key=$apiKey")
    val res = Http(svc OK as.String)
    res()
  }

  def movieByCode(code: String): String = {
    val svc = url(s"https://api.themoviedb.org/3/movie/$code?api_key=$apiKey")
    val res = Http(svc OK as.String)
    res()
  }

  def movieIdByTitle(title: String): Int = {
    val encoded = URLEncoder.encode(title, "UTF-8")
    val svc = url(s"https://api.themoviedb.org/3/search/movie?api_key=$apiKey&query=$encoded")
    val res = Http(svc OK as.String)
    val json: String = res()
    val total: String = compact(render(parse(json) \ "total_results"))
    if (total.toInt == 0) -1
    else {
      val idsRaw = compact(render(parse(json) \ "results" \ "id"))
      val regex = """(\d+)""".r
      regex.findFirstIn(idsRaw).map(idRaw => idRaw.toInt).getOrElse(-1)
    }
  }

  def topRatedMovies(): String = {
    val svc = url(s"https://api.themoviedb.org/3/movie/top_rated?api_key=$apiKey")
    val res = Http(svc OK as.String)
    res()
  }

  def similarMovies(movieCode: String): String = {
    val svc = url(s"https://api.themoviedb.org/3/movie/$movieCode/similar?api_key=$apiKey")
    val res = Http(svc OK as.String)
    res()
  }

  def genres(): List[String] = {
    val svc = url(s"https://api.themoviedb.org/3/genre/movie/list?api_key=$apiKey")
    val res = Http(svc OK as.String)
    val json: String = res()
    val genresRaw = compact(render(parse(json) \ "genres" \ "name"))
    val words = """(\w+)""".r
    words.findAllIn(genresRaw).toList
  }

  def keywords(title: String): List[String] = {
    val svc = url(s"https://api.themoviedb.org/3/search/keyword?api_key=$apiKey&query=$title")
    val res = Http(svc OK as.String)
    val json: String = res()
    val keywordsRaw = compact(render(parse(json) \ "results" \ "name"))
    val words = """(\w+)""".r
    words.findAllIn(keywordsRaw).toList.distinct
  }

  def tagline(movieCode: String): String = {
    val json: String = movieByCode(movieCode)
    val taglineRaw: String = compact(render(parse(json) \ "tagline"))
    taglineRaw.replaceAll("\"", "")
  }

  def popularPeople(): List[String] = {
    val svc = url(s"https://api.themoviedb.org/3/person/popular?api_key=$apiKey")
    val res = Http(svc OK as.String)
    val json: String = res()
    val keywordsRaw = compact(render(parse(json) \ "results" \ "name"))
    val words = """\w+( \w+)""".r
    words.findAllIn(keywordsRaw).toList.distinct
  }


}
