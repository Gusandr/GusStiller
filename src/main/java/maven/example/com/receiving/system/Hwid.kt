package maven.example.com.receiving.system

import maven.example.com.utility.data.Receipt
import maven.example.com.utility.data.Data
import maven.example.com.utility.data.TypeData
import java.security.MessageDigest

class Hwid : Receipt(TYPE) {

    override val data: Data?
        get() = Data(TYPE, getHwid())

    companion object {
        private val TYPE = TypeData.SYSTEM_HWID

        fun getHwid(): String {
            try {
                val toEncrypt =
                    System.getenv("COMPUTERNAME") + System.getProperty("user.name") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv(
                        "PROCESSOR_LEVEL"
                    )
                val md = MessageDigest.getInstance("MD5")
                md.update(toEncrypt.toByteArray())
                val hexString = StringBuffer()

                val byteData = md.digest()

                for (aByteData in byteData!!) {
                    val hex = Integer.toHexString(0xff and aByteData.toInt())
                    if (hex.length == 1) hexString.append('0')
                    hexString.append(hex)
                }

                return hexString.toString()
            } catch (e: Exception) {
                System.err.println("Error in HWID: " + e.message)
            }
            return ""
        }

    }
}
