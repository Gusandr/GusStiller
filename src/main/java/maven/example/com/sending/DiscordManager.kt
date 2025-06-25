package maven.example.com.sending

import maven.example.com.sending.discord.webhook.Client
import maven.example.com.sending.utility.SendClient

class DiscordManager {
    companion object {
        fun getDiscordSender(url: String): SendClient {
            return Client(url)
        }
    }
}