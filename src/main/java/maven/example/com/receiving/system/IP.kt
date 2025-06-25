package maven.example.com.receiving.system

import maven.example.com.receiving.utility.Receipt
import maven.example.com.utility.data.Data
import maven.example.com.utility.data.TypeData
import java.net.InetAddress
import java.net.UnknownHostException

class IP : Receipt(TYPE) {

    override val data: Data?
    get() = try {
        Data(TYPE, InetAddress.getLocalHost().toString())
    } catch (e: UnknownHostException) {
        System.err.println("Ошибка при получение IP адреса")
        e.printStackTrace()
        Data(TYPE, "empty")
    }

    companion object {
        private val TYPE = TypeData.SYSTEM_IP
    }
}
