package maven.example.com.receiving.system;


import maven.example.com.tools.data.Data;
import maven.example.com.tools.data.TypeData;
import maven.example.com.receiving.tools.Receipt;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Clipboard extends Receipt {
    private static final TypeData TYPE = TypeData.SYSTEM_CLIPBOARD;

    public Clipboard() {
        super(TYPE);
    }

    private String getClipboardText() {
        try {
            return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (HeadlessException | UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public Data getData() {
        return new Data(TYPE, getClipboardText());
    }
}
