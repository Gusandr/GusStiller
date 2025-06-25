package maven.example.com.receiving.utility

import maven.example.com.utility.data.Data
import maven.example.com.utility.data.TypeData

abstract class Receipt(val type: TypeData?) {
    abstract val data: Data?
}
