/*
 * This file is part of Kaddy.
 *
 * Copyright (C) 2018  Kaddy Team
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package kaddy

import co.aikar.commands.CommandConfig
import co.aikar.commands.CommandConfigProvider
import kaddy.data.GuildData
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

internal class KaddyCommandConfigProvider : CommandConfigProvider {
    override fun provide(event: MessageReceivedEvent): CommandConfig = GuildData.forGuild(event.guild)
}