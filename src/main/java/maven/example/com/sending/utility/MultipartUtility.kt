package maven.example.com.sending.utility

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

class MultipartUtility(
    private val requestURL: String,
    private val charset: String = "UTF-8"
) {
    private val boundary = "---------------------------${System.currentTimeMillis()}"
    private val httpConn: HttpURLConnection
    private val outputStream: OutputStream
    private val writer: PrintWriter

    init {
        httpConn = createConnection()
        httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=$boundary")
        httpConn.setRequestProperty("User-Agent", "Gus Client")
        outputStream = httpConn.outputStream
        writer = PrintWriter(OutputStreamWriter(outputStream, charset), true)
    }

    private fun createConnection(): HttpURLConnection {
        val connection = URL(requestURL).openConnection() as HttpURLConnection
        connection.useCaches = false
        connection.doOutput = true
        connection.doInput = true
        return connection
    }

    fun addFormField(name: String, value: String) {
        writer.apply {
            append("--$boundary$LINE_FEED")
            append("Content-Disposition: form-data; name=\"$name\"$LINE_FEED")
            append("Content-Type: text/plain; charset=$charset$LINE_FEED")
            append(LINE_FEED)
            append("$value$LINE_FEED")
            flush()
        }
    }

    @Throws(IOException::class)
    fun addFilePart(fieldName: String, uploadFile: File) {
        addFilePartHeader(fieldName, uploadFile.name)
        FileInputStream(uploadFile).use { inputStream ->
            inputStream.copyTo(outputStream)
        }
        completeFilePart()
    }

    @Throws(IOException::class)
    fun addFilePart(fieldName: String, fileName: String, uploadFile: ByteArray) {
        addFilePartHeader(fieldName, fileName)
        outputStream.write(uploadFile)
        completeFilePart()
    }

    private fun addFilePartHeader(fieldName: String, fileName: String) {
        val contentType = URLConnection.guessContentTypeFromName(fileName) ?: "application/octet-stream"

        writer.apply {
            append("--$boundary$LINE_FEED")
            append("Content-Disposition: form-data; name=\"$fieldName\"; filename=\"$fileName\"$LINE_FEED")
            append("Content-Type: $contentType$LINE_FEED")
            append("Content-Transfer-Encoding: binary$LINE_FEED")
            append(LINE_FEED)
            flush()
        }
    }

    private fun completeFilePart() {
        writer.apply {
            append(LINE_FEED)
            flush()
        }
    }

    @Throws(IOException::class)
    fun finish(): String {
        writer.apply {
            append("--$boundary--$LINE_FEED")
            close()
        }

        return when (val responseCode = httpConn.responseCode) {
            HttpURLConnection.HTTP_OK -> readResponse(httpConn.inputStream)
            else -> throw IOException("Server returned non-OK response: $responseCode")
        }
    }

    private fun readResponse(inputStream: InputStream): String {
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            return reader.lineSequence().joinToString(LINE_FEED)
        }
    }

    companion object {
        private const val LINE_FEED = "\r\n"
    }
}