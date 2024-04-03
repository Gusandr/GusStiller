package maven.example.com.sending.discord.webhook;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import maven.example.com.sending.tools.SendClient;

import java.io.File;

public class Client implements SendClient {
    private WebhookClient client;

    public Client(String url) {
        try {
            this.client = WebhookClient.withUrl(url);
        } catch (IllegalArgumentException e) {
            System.err.println("Неправильное URL для вебхука!");
            e.printStackTrace();
        }
    }

    public WebhookClient getClient() {
        return client;
    }

    @Override
    public void send(String msg) {
        client.send(msg);
    }

    @Override
    public void send(File file) {
        client.send(file);
    }

    @Override
    public void close() {
        client.close();
    }

    public void sendEmbed(String description, Integer color) {
        WebhookEmbed embed = new WebhookEmbedBuilder()
                .setColor(color)
                .setDescription(description)
                .build();

        client.send(embed);
    }
}
