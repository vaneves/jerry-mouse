import java.text.SimpleDateFormat
import java.util.*

class Logger {
    companion object {

        private fun output(type: String, message: String) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date = dateFormat.format(Date())
            val output = "[$date] $type $message\n"
            print(output)
        }

        fun info(message: String) = output("INFO", message)

        fun error(message: String) = output("ERROR", message)
    }
}