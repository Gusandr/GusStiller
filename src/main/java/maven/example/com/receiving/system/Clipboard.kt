package maven.example.com.receiving.system

import maven.example.com.utility.data.Receipt
import maven.example.com.utility.data.Data
import maven.example.com.utility.data.TypeData
import java.awt.HeadlessException
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.UnsupportedFlavorException
import java.io.IOException

class Clipboard() : Receipt(TYPE) {

    override val data: Data?
        get() = Data(TYPE, this.clipboardText)

    private val clipboardText: String?
        get() {
            try {
                if (Toolkit.getDefaultToolkit().systemClipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                    Toolkit.getDefaultToolkit().systemClipboard.getData(DataFlavor.stringFlavor) as String
                } else {
                    null // В буфере не текст (например изображение)
                }
            } catch (e: Exception) {
                System.err.println("Error in clipboard: ${e.localizedMessage}")
                e.printStackTrace()
            }

            return ""
        }

    companion object {
        private val TYPE = TypeData.SYSTEM_CLIPBOARD
    }
}
