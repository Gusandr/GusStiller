package maven.example.com

import maven.example.com.utility.Manager


object App {

    @JvmStatic
    fun main(args: Array<String>) {
        val resourceName = "/CHANGE_THIS.properties"
        val resourceStream = this::class.java.getResourceAsStream(resourceName)
            ?: throw IllegalStateException("Resource not found: $resourceName")

        Manager.start(resourceStream)
        println("Success!")
    }
}