package com.vaneves.jerrymouse.filesystem

import com.vaneves.jerrymouse.Logger
import com.vaneves.jerrymouse.exception.AccessDeniedException
import com.vaneves.jerrymouse.exception.FileNotFoundException
import java.io.File

class StaticFile {

    fun getFileContentByUri(uri: String): String {
        var path = uri
        if (path.endsWith("/")) {
            path += "index.html"
        }
        Logger.info("File: $uri")
        return getFileContent(path)
    }

    fun getFileContent(path: String): String {
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