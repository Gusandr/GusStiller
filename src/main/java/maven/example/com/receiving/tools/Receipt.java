package maven.example.com.receiving.tools;


import maven.example.com.tools.data.Data;
import maven.example.com.tools.data.TypeData;

public abstract class Receipt {
    private final TypeData TYPE;

    public Receipt(TypeData type) {
        TYPE = type;
    }

    public abstract Data getData();
    public TypeData getType() {
        return TYPE;
    }
}
