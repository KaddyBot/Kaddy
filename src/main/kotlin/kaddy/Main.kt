/*
 * This file is part of Kaddy.
 *
 * Copyright (C) 2017  Kaddy Team
 *
 * Kaddy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kaddy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Kaddy.  If not, see <http://www.gnu.org/licenses/>.
 */
package kaddy

import java.io.File
import java.lang.reflect.Method
import java.net.URL
import java.net.URLClassLoader

object KaddyHub {
    private var kaddyThread: Thread? = null
    private var stopMethod: Method? = null

    @JvmStatic
    fun startBotThread(args: Array<String>) {

        val classLoader = createBotClassLoader()
        val botClass = classLoader.loadClass("kaddy.KaddyBot")
        val mainMethod = botClass.getDeclaredMethod("main", Array<String>::class.java)
        stopMethod = botClass.getDeclaredMethod("stop")

        val botThread = Thread({ mainMethod.invoke(null, args) })

        botThread.contextClassLoader = classLoader
        botThread.isDaemon

        botThread.start()
    }

    private fun createBotClassLoader():  ClassLoader {
        return URLClassLoader(gatherClasses(), ClassLoader.getSystemClassLoader().parent)
    }

    private fun gatherClasses(): Array<URL> {
        val urls = mutableListOf<URL>()

        val file = File("./bot/build/classes/main")
        urls.add(file.toURI().toURL());

        for (f in File("./libs").listFiles()) {
            urls.add(f.toURI().toURL());
        }

        return urls.toTypedArray();
    }
}

fun main(args: Array<String>) {
    KaddyHub.startBotThread(args)
}