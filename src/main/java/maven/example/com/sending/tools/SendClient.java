package maven.example.com.sending.tools;

import java.io.File;

public interface SendClient {
    void send(String msg);
    void send(File file);
    void close();
}
