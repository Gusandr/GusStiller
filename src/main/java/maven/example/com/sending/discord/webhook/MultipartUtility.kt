package maven.example.com.sending.discord.webhook

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

class MultipartUtility {
    private var boundary: String? = null
    private var httpConn: HttpURLConnection? = null
    private var charset: String? = null
    private var outputStream: OutputStream? = null
    private var writer: PrintWriter? = null

    @Throws(IOException::class)
    constructor(requestURL: String, charset: String) {
        this.charset = charset

        boundary = "---------------------------" + System.currentTimeMillis()

        val url = URL(requestURL)
        httpConn = url.openConnection() as HttpURLConnection
        httpConn?.setUseCaches(false)
        httpConn?.setDoOutput(true) // indicates POST method
        httpConn?.setDoInput(true)
        httpConn?.setRequestProperty("Content-Type", "multipart/form-data; boundary=$boundary")
        httpConn?.setRequestProperty("User-Agent", "Gus Client")
        outputStream = httpConn?.getOutputStream()
        writer = PrintWriter(OutputStreamWriter(outputStream, charset), true)
    }

    fun addFormField(name: String?, value: String?) {
        writer!!.append("--").append(boundary).append(LINE_FEED)
        writer!!.append("Content-Disposition: form-data; name=\"").append(name).append("\"").append(LINE_FEED)
        writer!!.append("Content-Type: text/plain; charset=").append(charset).append(LINE_FEED)
        writer!!.append(LINE_FEED)
        writer!!.append(value).append(LINE_FEED)
        writer!!.flush()
    }

    @Throws(IOException::class)
    fun addFilePart(fieldName: String?, uploadFile: File?) {
        val fileName = uploadFile?.getName()
        writer!!.append("--").append(boundary).append(LINE_FEED)
        writer!!.append("Content-Disposition: form-data; name=\"").append(fieldName).append("\"; filename=\"")
            .append(fileName).append("\"").append(LINE_FEED)
        writer!!.append("Content-Type: ").append(URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED)
        writer!!.append("Content-Transfer-Encoding: binary").append(LINE_FEED)
        writer!!.append(LINE_FEED)
        writer!!.flush()

        val inputStream = FileInputStream(uploadFile)
        val buffer = ByteArray(4096)
        var bytesRead = -1
        while ((inputStream.read(buffer).also { bytesRead = it }) != -1) {
            outputStream!!.write(buffer, 0, bytesRead)
        }
        outputStream!!.flush()
        inputStream.close()

        writer!!.append(LINE_FEED)
        writer!!.flush()
    }

    @Throws(IOException::class)
    fun addFilePart(fieldName: String?, fileName: String?, uploadFile: ByteArray) {
        writer!!.append("--").append(boundary).append(LINE_FEED)
        writer!!.append("Content-Disposition: form-data; name=\"").append(fieldName).append("\"; filename=\"")
            .append(fileName).append("\"").append(LINE_FEED)
        writer!!.append("Content-Type: application/octet-stream").append(LINE_FEED)
        writer!!.append("Content-Transfer-Encoding: binary").append(LINE_FEED)
        writer!!.append(LINE_FEED)
        writer!!.flush()

        outputStream!!.write(uploadFile)

        writer!!.append(LINE_FEED)
        writer!!.flush()
    }

    @Throws(IOException::class)
    fun finish(): String {
        writer!!.append("--").append(boundary).append("--").append(LINE_FEED)
        writer!!.close()

        val responseCode = httpConn!!.getResponseCode()
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(InputStreamReader(httpConn!!.getInputStream()))
            var line: String?
            val stringBuilder = StringBuilder()
            while ((reader.readLine().also { line = it }) != null) {
                stringBuilder.append(line).append(LINE_FEED)
            }
            reader.close()
            return stringBuilder.toString()
        } else {
            throw IOException("Server returned non-OK response: $responseCode")
        }
    }

    companion object {
        const val LINE_FEED: String = "\r\n"
    }
}