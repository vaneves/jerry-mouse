package com.vaneves.jerrymouse

import com.vaneves.jerrymouse.response.Response
import com.vaneves.jerrymouse.exception.FileNotFoundException
import com.vaneves.jerrymouse.exception.AccessDeniedException
import com.vaneves.jerrymouse.filesystem.PathNormalizer
import com.vaneves.jerrymouse.filesystem.StaticFile
import com.vaneves.jerrymouse.request.Request
import java.lang.Exception
import java.net.Socket

class Application (private val config: Config, private val client: Socket) {

    fun run() {
        val normalizer = PathNormalizer(config.get("ROOT"))
        val staticFile = StaticFile()
        val request = Request(client)
        var code = 200
        var body = ""
        try {
            body = staticFile.getFileContentByUri(normalizer.getFullPath(request.getUri()))
        } catch (e: FileNotFoundException) {
            code = 404
            body = staticFile.getFileContent(normalizer.getFullPath("404.html"))
            Logger.error("File not found")
        } catch (e: AccessDeniedException) {
            code = 403
            body = staticFile.getFileContent(normalizer.getFullPath("403.html"))
            Logger.error("Access denied")
        } catch (e: Exception) {
            code = 500
            body = staticFile.getFileContent(normalizer.getFullPath("500.html"))
            Logger.error("Internal server error: ${e.message}")
        }
        val response = Response(client)
        response.send(body, code)
        client.close()
    }
}