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
package kaddy.commands

import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Conditions
import co.aikar.commands.annotation.Subcommand
import kaddy.KaddyBot
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

@CommandAlias("bot")
@Conditions("owneronly")
class BotManagementCommands(bot: KaddyBot) : KaddyBaseCommand(bot) {

    @Subcommand("update")
    @Conditions("owneronly")
    fun update(event: MessageReceivedEvent) {
        bot.attemptUpdate(event.channel)
        //bot.queueIfOwner(event.author, { bot.attemptUpdate(event.channel) })
    }
}