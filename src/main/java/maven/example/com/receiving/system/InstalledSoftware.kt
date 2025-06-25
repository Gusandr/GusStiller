package maven.example.com.receiving.system

import maven.example.com.receiving.utility.Receipt
import maven.example.com.utility.data.Data
import maven.example.com.utility.data.TypeData
import java.io.*
import java.util.*

class InstalledSoftware : Receipt(TYPE) {

    override val data: Data?
        get() = Data(TYPE, GettingInstalledPrograms.programs)

    companion object {
        private val TYPE = TypeData.SYSTEM_INSTALLED_SOFTWARE
    }
}

internal object GettingInstalledPrograms {
    val programs: String?
        get() {
            val sw = StringWriter()

            try {
                val processBuilder =
                    ProcessBuilder(*command.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
                val process = processBuilder.redirectErrorStream(true).start()
                val reader = BufferedReader(InputStreamReader(process.inputStream))
                InputStreamReader.transferTo(reader, sw)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return sw.toString()
        }

    private val command: String
        get() {
            val os = System.getProperty("os.name").lowercase(Locale.getDefault())

            if (os.contains("window")) return "powershell.exe Get-ItemProperty HKLM:\\Software\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\* | Select-Object DisplayName, DisplayVersion" // Другие команды занимают 25 секунд

            if (os.contains("linux")) return "sudo dpkg --get-selections"
            return "pkgutil --pkgs" // for MacOS
        }

    internal object InputStreamReader {
        @Throws(IOException::class)
        fun readFullyAsString(inputStream: InputStream, encoding: String?): String? {
            return readFully(inputStream).toString(encoding)
        }

        @Throws(IOException::class)
        fun readFullyAsBytes(inputStream: InputStream): ByteArray {
            return readFully(inputStream).toByteArray()
        }

        @Throws(IOException::class)
        private fun readFully(inputStream: InputStream): ByteArrayOutputStream {
            val baos = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var length: Int
            while ((inputStream.read(buffer).also { length = it }) != -1) {
                baos.write(buffer, 0, length)
            }
            return baos
        }

        @Throws(IOException::class)
        fun transferTo(reader: BufferedReader, out: Writer?): Long {
            Objects.requireNonNull<Writer?>(out, "out")
            var transferred = 0L
            val buffer = CharArray(8192)

            var nRead: Int
            while ((reader.read(buffer, 0, 8192).also { nRead = it }) >= 0) {
                out!!.write(buffer, 0, nRead)
                if (transferred < Long.Companion.MAX_VALUE) {
                    try {
                        transferred = Math.addExact(transferred, nRead.toLong())
                    } catch (_: ArithmeticException) {
                        transferred = Long.Companion.MAX_VALUE
                    }
                }
            }

            return transferred
        }
    }
}