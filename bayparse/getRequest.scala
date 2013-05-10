import scala.io.Source.{fromInputStream}
import java.net._

object getRequest{
	def getUrl(site:String):String = {
		val url = new URL(site)
		val content = fromInputStream( url.openStream ).getLines.mkString("\n")
		return content
	}
	def getBay() :String = {
		val content = getRequest.getUrl("http://thepiratebay.se")
		return content
	}
	def main(args: Array[String]){
		val x = getBay()
		println(x)

	}
	def new(){
		println("hello world")
	}
}