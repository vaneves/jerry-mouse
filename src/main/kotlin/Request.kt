import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

class Request {
    private var uri = "";

    constructor(client: Socket) {
        val input = BufferedReader(InputStreamReader(client.inputStream))
        var line = input.readLine()
        uri = line.split(" ")[1]
    }

    fun getUri() = uri
}