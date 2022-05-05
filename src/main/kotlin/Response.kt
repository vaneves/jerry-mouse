import java.io.PrintWriter
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.Date

class Response (private val client: Socket) {

    fun send(body: String, code: Int = 200) {
        val output = PrintWriter(client.getOutputStream(), true)
        output.println("HTTP/1.1 $code OK")
        output.println("Date: "+ SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(Date()))
        output.println("Server: JerryMouse/0.0.1Alpha")
        output.println("Cache-Control: no-cache")
        output.println("Keep-Alive: timeout=3, max=100")
        output.println("Connection: Keep-Alive")
        output.println()
        output.println(body)
        output.close()
    }
}