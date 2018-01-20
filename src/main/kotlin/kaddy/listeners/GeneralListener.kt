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
package kaddy.listeners

import kaddy.KaddyBot
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

internal class GeneralListener(private val bot: KaddyBot) : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent?) {
        if (event!!.message.contentRaw == ">update") {
            bot.attemptUpdate()
        }
    }
}