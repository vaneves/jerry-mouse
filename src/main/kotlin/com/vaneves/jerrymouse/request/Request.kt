package com.vaneves.jerrymouse.request

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

class Request(client: Socket) {
    private var uri = "";

    init {
        val input = BufferedReader(InputStreamReader(client.inputStream))
        val line = input.readLine()
        if (line != null) {
            val parts = line.split(" ")
            uri = parts[1]
        }
    }

    fun getUri() = uri
}