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

import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Game

class Presence internal constructor (private val presence: net.dv8tion.jda.core.managers.Presence) {

    var game: Game?
        get() = presence.game
        set(value) {
            presence.game = value
        }

    var status: OnlineStatus
        get() = presence.status
        set(value) {
            presence.status = value
        }

    var idle: Boolean
        get() = presence.isIdle
        set(value) {
            presence.isIdle = value
        }

    fun setPresence(status: OnlineStatus, game: Game?) {
        presence.setPresence(status, game)
    }

    fun setPresence(status: OnlineStatus, idle: Boolean) {
        presence.setPresence(status, idle)
    }

    fun setPresence(game: Game?, idle: Boolean) {
        presence.setPresence(game, idle)
    }

    fun setPresence(status: OnlineStatus, game: Game?, idle: Boolean) {
        presence.setPresence(status, game, idle)
    }
}