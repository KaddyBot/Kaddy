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