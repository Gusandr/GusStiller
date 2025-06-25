package maven.example.com.sending.utility

import java.io.File

interface SendClient {
    fun send(msg: String?)
    fun send(file: File?)
    fun close()
}