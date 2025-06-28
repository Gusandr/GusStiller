package maven.example.com.receiving.system

import maven.example.com.utility.data.Receipt
import maven.example.com.utility.data.Data
import maven.example.com.utility.data.TypeData

class Location : Receipt(TYPE) {

    override val data: Data?
        get() {
            val lang = "Language: " + System.getProperty("user.language")
            val country = "Country: " + System.getProperty("user.country")
            return Data(TYPE, lang + "\n" + country)
        }

    companion object {
        private val TYPE = TypeData.SYSTEM_LOCATION
    }
}
