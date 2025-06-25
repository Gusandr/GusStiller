package maven.example.com.utility.archive

import maven.example.com.utility.data.Data
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.function.Consumer
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class Archive {
    private var datas: MutableList<Data?>? = null

    constructor(list: MutableList<Data?>?) {
        this.datas = ArrayList()
        datas?.addAll(list?:throw NullPointerException())
    }

    @Throws(IOException::class)
    fun filesToArchive(nameArchive: String?): File {
        val archive = File(nameArchive?:throw NullPointerException())
        archive.createNewFile()
        val fos = FileOutputStream(archive)
        val zipOut = ZipOutputStream(fos)

        // .txt файл в архив
        datas!!.forEach(Consumer { data: Data? ->
            try {
                val fileToZip = data!!.toFile()
                val fis = FileInputStream(data.toFile())
                val zipEntry = ZipEntry("${data.category}\\${fileToZip.name}")
                zipOut.putNextEntry(zipEntry)

                val bytes = ByteArray(1024)
                var length: Int
                while ((fis.read(bytes).also { length = it }) >= 0) {
                    zipOut.write(bytes, 0, length)
                }

                fis.close()
                fileToZip.delete()
            } catch (e: Exception) {
                //Log.error("Ошибка при помещения лога в архив!\n${e.stackTraceToString()}")
            }
        })

        zipOut.closeEntry()
        zipOut.close()

        return archive
    }
}