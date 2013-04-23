import getRequest.getUrl

object bay{
	def getBay() :String = {
		val content = getRequest.getUrl("http://thepiratebay.se")
		return content
	}
	def searchBay(query:String,method:String):Array[String] = {
		val sUrl = "http://thepiratebay.se/search/" + query + method
		println(sUrl)
		val content = getRequest.getUrl(sUrl)
		val sContent = content.split("\n")
		return sContent
	}
	def setSearch(method:String):String = {
		var dmethod = "/0/7/0"
		if (method == "seed"){
			dmethod = "/0/7/0"
		}
		else{
			dmethod = "/0/99/0"
		}
		return dmethod
	}
	def printRawLines(html:String):Array[String] = {
		val lines = html.split("\n")
		val lineCount = lines.length
		for (x <- 1 to(lineCount - 1)){
			println(lines.deep(x))
		}
		return lines
	}
	def getMagnets(x:Array[String]){
		for (a <- 1 to (x.length - 1)){
			val mLines = x.deep(a).toString
			if (mLines contains "<a href=\"magnet"){
				println("")
				println(a)
				val title = mLines.split("=")
				val nTitle = title.deep(3).toString
				val eTitle = nTitle.replace("+"," ")
				println(eTitle)
				println("")
				val mLink = mLines.split("\"")
				println(mLink.deep(1))
			}

		}
	}

	def main(args:Array[String]){
		val site = getBay()
		println(site)
	}
}