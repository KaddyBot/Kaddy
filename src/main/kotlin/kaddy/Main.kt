package kaddy

import java.io.File
import java.net.URL
import java.net.URLClassLoader

fun main(args: Array<String>) {

    val urls = mutableListOf<URL>()

    val file = File("./bot/build/classes/main")
    urls.add(file.toURI().toURL());

    for (f in File("./libs").listFiles()) {
        urls.add(f.toURI().toURL());
    }

    val classLoader = URLClassLoader(urls.toTypedArray(), ClassLoader.getSystemClassLoader().parent)

    val botClass = classLoader.loadClass("kaddy.KaddyBot")
    val mainMethod = botClass.getDeclaredMethod("main", Array<String>::class.java)

    Thread.currentThread().contextClassLoader = classLoader

    mainMethod.invoke(null, args)

}