import getRequest.getUrl

object bay{
	def getBay() :String = {
		val content = getRequest.getUrl("http://thepiratebay.se/search/bones/0/7/0")
		return content
	}
	def searchBay(method:String,search:String):String = {
		var dmethod = "/0/7/0"
		if (method == "seed"){
			dmethod = "/0/7/0"
		}
		else{
			dmethod = "/0/99/0"
		}
		return dmethod
	}
	def main(args:Array[String]){
		val site = getBay()
		println(site)
	}
}