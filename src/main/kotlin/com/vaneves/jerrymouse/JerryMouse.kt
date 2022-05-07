package com.vaneves.jerrymouse

import java.net.InetAddress
import java.net.ServerSocket

class JerryMouse(configPath: String) {
    private val config: Config = Config(configPath)

    fun run() {
        val port = config.get("PORT").toInt()
        val backlog = config.get("BACKLOG").toInt()
        val ip = config.get("IP")
        val ipAddress = InetAddress.getByName(ip)
        val server = ServerSocket(port, backlog, ipAddress)
        Logger.info("Server running: $ip:$port")
        while (true) {
            val socket = server.accept()
            Logger.info("Client connected")
            Thread {
                val app = Application(config, socket)
                app.run()
            }.start()
        }
    }
}