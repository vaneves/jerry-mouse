import exception.FileNotFoundException
import exception.AccessDeniedException
import java.lang.Exception
import java.net.Socket
import java.io.File

class Application (private val client: Socket) {

    fun run() {
        val request = Request(client)
        var code = 200
        var body = ""
        try {
            body = getFileContentByUri(request.getUri())
        } catch (e: FileNotFoundException) {
            code = 404
            body = getFileContent("./www/404.html")
            Logger.error("File not found")
        } catch (e: AccessDeniedException) {
            code = 403
            body = getFileContent("./www/403.html")
            Logger.error("Access denied")
        } catch (e: Exception) {
            code = 500
            body = getFileContent("./www/500.html")
            Logger.error("Internal server error: ${e.message}")
        }
        val response = Response(client)
        response.send(body, code)
        client.close()
    }

    private fun getFileContentByUri(uri: String): String {
        Logger.info("File: $uri")
        var path = "./www/$uri"
        if (path.endsWith("/")) {
            path += "index.html"
        }
        return getFileContent(path)
    }

    private fun getFileContent(path: String): String {
        val file = File(path)
        if (! file.exists()) {
            throw FileNotFoundException()
        }
        if (! file.canRead()) {
            throw AccessDeniedException()
        }
        return file.readText()
    }
}