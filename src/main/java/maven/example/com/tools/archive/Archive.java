package maven.example.com.tools.archive;

import maven.example.com.tools.data.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Archive {
    private List<Data> datas;

    public Archive(List<Data> list) {
        this.datas = new ArrayList<>();
        datas.addAll(list);
    }

    public File filesToArchive(String nameArchive) throws IOException {
        File archive = new File(nameArchive);
        archive.createNewFile();
        FileOutputStream fos = new FileOutputStream(archive);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        // .txt файл в архив
        datas.forEach(data -> {
            try {
                File fileToZip = data.toFile();
                FileInputStream fis = new FileInputStream(data.toFile());
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }

                fis.close();
                fileToZip.delete();
            } catch (Exception e) {
                System.err.println("Ошибка при помещения лога в архив!");
                e.printStackTrace();
            }
        });

        zipOut.closeEntry();
        zipOut.close();

        return archive;
    }
}
