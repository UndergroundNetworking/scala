object fuzz{
	import java.net._
	import java.io._
	import scala.io._


	def main(args:Array[String]){
		openTheServer(4321)
	}
	def openTheServer(spt:Int){
		println("Starting a server on port " + spt.toString)
		val server = new ServerSocket(spt)
		println("Server is up")
		while (true){
			println("Waiting for connection")
			val s = server.accept()
			val in = new BufferedSource(s.getInputStream()).getLines()
			val out = new PrintStream(s.getOutputStream())
			val resp = in.next()
			println("Connection Established")
			if (resp.toString == "SYN"){
				println("Connection Authenticated - ACK ing")
				out.println("ACK")

			}
			else{
				println("Connection Closed - Bad SYN")
				out.println("RST")
				out.flush()
			}
			s.close()
		}
		
	}
	def connectClient(ip:String, pt:Int, cm:String){
		println("Trying connection to " + ip + " on port " + pt.toString)
		val s = new Socket(ip,pt)
		println("Connection Open ")
		lazy val in = new BufferedSource(s.getInputStream()).getLines()
		val out = new PrintStream(s.getOutputStream())
		println("Output stream initiated")
		out.println(cm)
		out.flush()
		println("Received: " + in.next() + " from server")
		s.close()
	}
	def fuzz(cip:String, cpt:Int){
		var payload = "A"
		val multiplyer = 8
		var increasePayload = 1
		while (increasePayload < 3000){
			Thread.sleep(2000)
			println("Fuzzing " + cip + " with " + increasePayload.toString + " A's")
			connectClient(cip,cpt,payload)
			increasePayload = increasePayload + multiplyer
			payload = payload * increasePayload
		}

	}
}