package maven.example.com.tools.config;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
    public static Config readConfigFromFile(InputStream inputStream) {
        Properties prop = new Properties();
        loadProperties(inputStream, prop);

        Config config = Config.getInstance();
        config.setProp(prop);

        return config;
    }

    private static void loadProperties(InputStream inputStream, Properties prop) {
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            System.err.println("Произошла ошибка IOException!\nФайл: " + inputStream + "\nНастройки: " + prop);
            e.printStackTrace();
        }
    }
}
