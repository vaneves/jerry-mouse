import java.net.ServerSocket

class JerryMouse {
    private val PORT: Int = 9999
    fun run() {
        val server = ServerSocket(PORT)
        Logger.info("Server running: localhost:$PORT")
        while (true) {
            val socket = server.accept()
            Logger.info("Client connected")
            Thread {
                val app = Application(socket)
                app.run()
            }.start()
        }
    }
}