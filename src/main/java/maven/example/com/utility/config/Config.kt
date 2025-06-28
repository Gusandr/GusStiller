package maven.example.com.utility.config

import java.io.IOException
import java.io.InputStream
import java.util.*

object Config {
    var prop: Properties? = null

    fun readConfigFromFile(inputStream: InputStream?): Config {
        val prop = Properties()
        loadProperties(inputStream, prop)

        this.prop = prop

        return this
    }

    private fun loadProperties(inputStream: InputStream?, prop: Properties) {
        try {
            prop.load(inputStream)
        } catch (e: IOException) {
            System.err.println("Произошла ошибка IOException в loadProperties():\n${e.stackTraceToString()}\nФайл: $inputStream\nНастройки: $prop")
        }
    }
}