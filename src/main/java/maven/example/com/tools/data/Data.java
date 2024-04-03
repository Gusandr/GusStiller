package maven.example.com.tools.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Data {
    private TypeData category;
    private String data;

    public Data(TypeData category, String data) {
        this.category = category;
        this.data = isEmptyString(data);
    }

    private static String isEmptyString(String str) {
        if (str == null || str.isEmpty())
            return "empty";

        return str;
    }

    public File toFile() throws IOException {
        File file = new File(category.getStringType() + ".txt");
        FileWriter writer = new FileWriter(file);

        writer.write(this.data);
        writer.close();
        return file;
    }

    @Override
    public String toString() {
        return category + ":\n" + data;
    }
}
