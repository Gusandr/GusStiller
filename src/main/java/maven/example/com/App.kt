package maven.example.com

import maven.example.com.utility.Manager


object App {

    @JvmStatic
    fun main(args: Array<String>) {
        val resourceStream = object {}.javaClass.getResourceAsStream("/CHANGE_THIS.properties")
            ?: throw IllegalStateException("Resource not found: /CHANGE_THIS.properties")

        Manager.start(resourceStream)
        println("Good!")
    }
}