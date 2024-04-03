package maven.example.com.receiving.system;

import maven.example.com.tools.data.Data;
import maven.example.com.tools.data.TypeData;
import maven.example.com.receiving.tools.Receipt;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IP extends Receipt {
    private static final TypeData TYPE = TypeData.SYSTEM_IP;

    public IP() {
        super(TYPE);
    }

    @Override
    public Data getData() {
        try {
            return new Data(TYPE, InetAddress.getLocalHost().toString());
        } catch (UnknownHostException e) {
            System.err.println("Ошибка при получение IP адреса");
            e.printStackTrace();
            return new Data(TYPE, "empty");
        }
    }
}
