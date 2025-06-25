package maven.example.com.utility.data

import java.io.File
import java.io.FileWriter
import java.io.IOException

class Data(val category: TypeData, data: String?) {
    private val data: String = getNotEmptyString(data)

    @Throws(IOException::class)
    fun toFile(): File {
        val file = File("${category.stringType}.txt")

        val writer = FileWriter(file)

        writer.write(this.data)
        writer.close()

        return file
    }

    override fun toString(): String {
        return "$category:\n$data"
    }

    companion object {
        private fun getNotEmptyString(str: String?): String {
            if (str == null || str.isEmpty()) return "empty"

            return str
        }
    }
}
