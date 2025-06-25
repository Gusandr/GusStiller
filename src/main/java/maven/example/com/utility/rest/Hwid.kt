package maven.example.com.utility.rest

import java.security.MessageDigest

object Hwid {
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
            //Log.error("Ошибка в getHwid():\n${e.message}")
        }
        return ""
    }
}