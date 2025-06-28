package maven.example.com.receiving.system

import maven.example.com.utility.data.Receipt
import maven.example.com.utility.data.Data
import maven.example.com.utility.data.TypeData
import java.util.function.Consumer

/*
    https://www.roseindia.net/java/beginners/OSInformation.shtml
 */
class SystemInformationJava : Receipt(TYPE) {

    override val data: Data?
        get() {
            val sb = StringBuilder()
            val keys: MutableList<String> = ArrayList()
            keys.add("java.version")
            keys.add("java.vendor")
            keys.add("java.home")
            keys.add("java.vm.specification.version")
            keys.add("java.vm.specification.vendor")
            keys.add("java.vm.specification.name")
            keys.add("java.vm.version")
            keys.add("java.vm.vendor")
            keys.add("java.vm.name")
            keys.add("java.specification.version")
            keys.add("java.specification.vendor")
            keys.add("java.specification.name")
            keys.add("java.class.version")
            keys.add("java.class.path")
            keys.add("java.library.path")
            keys.add("java.io.tmpdir")
            keys.add("java.compiler")
            keys.add("java.ext.dirs")
            keys.add("os.name")
            keys.add("os.arch")
            keys.add("os.version")
            keys.add("file.separator")
            keys.add("path.separator")
            keys.add("line.separator")
            keys.add("user.name")
            keys.add("user.home")
            keys.add("user.dir")

            keys.forEach(Consumer { key: String ->
                sb
                    .append(key)
                    .append(": ")
                    .append(System.getProperty(key))
                    .append("\n")
            })

            return Data(TYPE, sb.toString())
        }

    companion object {
        private val TYPE = TypeData.SYSTEM_INFORMATION_JAVA
    }
}
