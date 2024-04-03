package maven.example.com.sending;

import maven.example.com.sending.discord.webhook.Client;
import maven.example.com.sending.tools.SendClient;

public class Manager {
    public static SendClient getDiscordSendMessage(String url) {
        return new Client(url);
    }
}
