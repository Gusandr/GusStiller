package maven.example.com.sending.discord

import maven.example.com.sending.utility.MultipartUtility
import maven.example.com.sending.utility.SendClient
import maven.example.com.utility.crypt.Crypto
import java.io.File
import kotlin.String

import java.nio.charset.StandardCharsets

class Client : SendClient {
    private var client: MultipartUtility? = null

    constructor(cryptUrl: String) {
        try {
            val fullUrl = Crypto.decrypt(cryptUrl)
            this.client = MultipartUtility(fullUrl, StandardCharsets.UTF_8.name())
        } catch (e: Exception) {
            System.err.println("Неправильное URL для вебхука!")
            e.printStackTrace()
        }
    }

    fun clientCheckNull() {
        if (client == null)
            throw Exception("client is null!")
    }

    override fun send(msg: String?) {
        clientCheckNull()
        if (msg != null)
            client?.addFormField("LogString", msg)
    }

    override fun send(file: File?) {
        clientCheckNull()
        if (file != null)
            client?.addFilePart("LogFile", file)
    }

    override fun close() {
        client?.finish()
        client = null
    }
}