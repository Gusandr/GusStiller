package maven.example.com.receiving.system

import maven.example.com.receiving.utility.Receipt
import maven.example.com.utility.data.Data
import maven.example.com.utility.data.TypeData

class TimeZone : Receipt(TYPE) {

    override val data: Data?
        get() = Data(TYPE, java.util.TimeZone.getDefault().displayName)

    companion object {
        private val TYPE = TypeData.SYSTEM_TIMEZONE
    }
}
