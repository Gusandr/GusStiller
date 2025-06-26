package maven.example.com.utility.crypt

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/*
    Воспользуйтесь обфускатором указанном в pom.xml во избежания кражи вашего вебхука, эта шифровка не гарантия защиты.
    Вставьте сюда ваш KEY и IV и укажите в параметре discord.webhook.url ваш зашифрованный вебхук (шифровка делается вами)
    Формат перед зашифровкой: https://discord.com/api/webhooks/xxx/xxx
 */
object Crypto {
    private const val ALGORITHM = "AES/CBC/PKCS5Padding"
    private const val KEY = "F2RuehFH4jr12FHr"
    private const val IV = "oSoi234fA23pFHo1"

    fun decrypt(input: String): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        val keySpec = SecretKeySpec(KEY.toByteArray(), "AES")
        val ivSpec = IvParameterSpec(IV.toByteArray())
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        val decoded = Base64.getDecoder().decode(input)
        val decrypted = cipher.doFinal(decoded)
        return String(decrypted)
    }
}