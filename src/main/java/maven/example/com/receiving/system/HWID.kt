package maven.example.com.receiving.system

import maven.example.com.receiving.utility.Receipt
import maven.example.com.utility.data.Data
import maven.example.com.utility.data.TypeData
import maven.example.com.utility.rest.Hwid

class HWID : Receipt(TYPE) {

    override val data: Data?
        get() = Data(TYPE, Hwid.getHwid())

    companion object {
        private val TYPE = TypeData.SYSTEM_HWID
    }
}
