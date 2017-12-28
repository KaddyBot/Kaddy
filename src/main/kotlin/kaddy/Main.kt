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

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
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

        kaddyThread = Thread({ mainMethod.invoke(null, args) }, "Kaddy")

        kaddyThread!!.contextClassLoader = classLoader

        kaddyThread!!.start()
    }

    fun stopBotThread() {
        stopMethod?.invoke(null)
        kaddyThread = null
        stopMethod = null
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

    val botRunning: Boolean
        get() = kaddyThread != null
}

fun main(args: Array<String>) {
    val terminal = DefaultTerminalFactory().createTerminal()
    terminal.setCursorVisible(false)

    val graphics = terminal.newTextGraphics()

    var keyStroke: KeyStroke? = null

    while (keyStroke == null || keyStroke.character != '2') {
        terminal.clearScreen()
        graphics.putString(0, 0, "Welcome to Kaddy!")
        graphics.putString(0, 3, "Please select an option:")
        if (KaddyHub.botRunning) {
            graphics.putString(2, 5, "1. Stop bot")
        } else {
            graphics.putString(2, 5, "1. Start bot")
        }
        graphics.putString(2, 6, "2. Exit")

        terminal.flush()

        keyStroke = terminal.readInput()

        try {
            if (keyStroke?.character == '1') {
                if (KaddyHub.botRunning) {
                    KaddyHub.stopBotThread()
                } else {
                    KaddyHub.startBotThread(args)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    if (KaddyHub.botRunning) {
        KaddyHub.stopBotThread()
    }

    terminal.close()

    //KaddyHub.startBotThread(args)
}