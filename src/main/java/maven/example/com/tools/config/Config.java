package maven.example.com.tools.config;

import java.util.Properties;

public final class Config {
    private static Config INSTANCE;

    private Properties prop;

    private Config() {}

    public static Config getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Config();

        return INSTANCE;
    }

    public static void setINSTANCE(Config INSTANCE) {
        Config.INSTANCE = INSTANCE;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }
}