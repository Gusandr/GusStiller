package maven.example.com.receiving.system;

import maven.example.com.receiving.tools.Receipt;
import maven.example.com.tools.data.Data;
import maven.example.com.tools.data.TypeData;
import maven.example.com.tools.rest.Hwid;

public class HWID extends Receipt {
    private static final TypeData TYPE = TypeData.SYSTEM_HWID;

    public HWID() {
        super(TYPE);
    }

    @Override
    public Data getData() {
        return new Data(TYPE, Hwid.getHWID());
    }
}
