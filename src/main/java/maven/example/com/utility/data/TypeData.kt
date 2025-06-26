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
    BROWSER_CHROME;

    val stringType: String
        get() {
            val type: String = when (this) {
                SYSTEM_CLIPBOARD -> "Clipboard"
                SYSTEM_SCREEN -> "Screenshot"
                SYSTEM_IP -> "IP"
                SYSTEM_HWID -> "HWID"
                SYSTEM_INFORMATION_JAVA -> "SystemInformation"
                SYSTEM_PROCESS_LIST -> "ProcessList"
                SYSTEM_INSTALLED_SOFTWARE -> "DownloadedSoftware"
                SYSTEM_LOCATION -> "Location"
                SYSTEM_TIMEZONE -> "TimeZone"
                else -> "Unknown data type_${Random().nextLong()}"
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