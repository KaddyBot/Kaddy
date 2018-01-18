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

import com.google.common.util.concurrent.FutureCallback
import de.btobastian.javacord.ImplDiscordAPI
import de.btobastian.javacord.entities.*
import de.btobastian.javacord.entities.message.Message
import de.btobastian.javacord.entities.permissions.Permissions
import de.btobastian.javacord.entities.permissions.PermissionsBuilder
import de.btobastian.javacord.entities.permissions.impl.ImplPermissionsBuilder
import de.btobastian.javacord.utils.ThreadPool
import de.btobastian.javacord.utils.ratelimits.RateLimitManager
import dtmlibs.logging.Loggable
import kaddy.util.KaddyLoggable
import java.awt.image.BufferedImage
import java.util.concurrent.Future

internal class KaddyImpl(internal val api: ImplDiscordAPI) : Kaddy, Loggable by KaddyLoggable {

    override var game: String?
        get() = api.game
        set(value) { api.game = value }
    override var streamingUrl: String?
        get() = api.streamingUrl
        set(value) { api.setGame(game, value) }
    override var isIdle: Boolean
        get() = api.isIdle
        set(value) { api.isIdle = value }
    override var isAutoReconnecting: Boolean
        get() = api.isAutoReconnectEnabled
        set(value) { api.setAutoReconnect(value) }
    override var isWaitingForServersOnStartup: Boolean
        get() = api.isWaitingForServersOnStartup
        set(value) { api.setWaitForServersOnStartup(value) }
    override var messageCacheSize: Int
        get() = api.messageCacheSize
        set(value) { api.messageCacheSize = value }
    override val botUser: User
        get() = api.yourself
    override val servers = DefaultMappedCollection<CharSequence, Server>({ api.servers },
            { api.getServerById(it.toString()) })
    override val users = DefaultMappedCollection<CharSequence, User>({ api.users },
            { api.getCachedUserById(it.toString()) })
    override val channels = DefaultMappedCollection<CharSequence, Channel>({ api.channels },
            { api.getChannelById(it.toString()) })
    override val voiceChannels = DefaultMappedCollection<CharSequence, VoiceChannel>({ api.voiceChannels },
            { api.getVoiceChannelById(it.toString()) })
    override val rateLimitManager: RateLimitManager
        get() = api.rateLimitManager
    override val threadPool: ThreadPool
        get() = api.threadPool

    override fun getUser(userId: CharSequence): Future<User> = api.getUserById(userId.toString())

    override fun getMessage(messageId: CharSequence): Message? = api.getMessageById(messageId.toString())

    override fun acceptInvite(inviteCode: CharSequence, callback: FutureCallback<Server>?): Future<Server>
            = api.acceptInvite(inviteCode.toString(), callback)

    override fun createServer(name: CharSequence, region: Region,
                              icon: BufferedImage?, callback: FutureCallback<Server>?): Future<Server>
            = api.createServer(name.toString(), region, icon, callback)

    override fun parseInvite(invite: CharSequence, callback: FutureCallback<Invite>?): Future<Invite>
            = api.parseInvite(invite.toString(), callback)

    override fun deleteInvite(inviteCode: CharSequence): Future<Void> = api.deleteInvite(inviteCode.toString())

    override fun createPermissionsBuilder(permissions: Permissions?): PermissionsBuilder
            = if (permissions == null) ImplPermissionsBuilder() else ImplPermissionsBuilder(permissions)
}