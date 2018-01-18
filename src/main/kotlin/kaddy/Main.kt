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
import java.io.PrintWriter

object KaddyHub {
    private var botProcess: Process? = null
    private var input: PrintWriter? = null

    @JvmStatic
    fun startBot(args: Array<String>) {
        val newArgs = mutableListOf("java", "-cp", "./bot/build/libs/bot.jar;./libs/*", "kaddy.KaddyBot", "-d")
        newArgs.addAll(args)
        val processBuilder = ProcessBuilder()
        processBuilder.command(newArgs)
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
        botProcess = processBuilder.start()
        input = PrintWriter(botProcess!!.outputStream)
    }

    fun stopBot() {
        input?.println("stop")
        input?.flush()
        botProcess?.waitFor()
        botProcess = null
    }

    val botRunning: Boolean
        get() = botProcess != null
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
                    KaddyHub.stopBot()
                } else {
                    KaddyHub.startBot(args)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    if (KaddyHub.botRunning) {
        KaddyHub.stopBot()
    }

    terminal.close()

    System.exit(0);
}