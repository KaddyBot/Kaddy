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
import com.sun.webkit.plugin.PluginManager
import de.btobastian.javacord.entities.*
import de.btobastian.javacord.entities.message.Message
import de.btobastian.javacord.entities.permissions.Permissions
import de.btobastian.javacord.entities.permissions.PermissionsBuilder
import de.btobastian.javacord.utils.ThreadPool
import de.btobastian.javacord.utils.ratelimits.RateLimitManager
import dtmlibs.logging.LogOwner
import java.awt.image.BufferedImage
import java.util.concurrent.Future

interface Kaddy : LogOwner {
    /**
     * The name of the game shown under the bot's name in the user list.
     */
    var game: String?
    /**
     * Sets the streaming url for the bot which if not null will show the bot as streaming.
     */
    var streamingUrl: String?
    /**
     * Whether the bot is idle or not.
     */
    var isIdle: Boolean
    /**
     * Whether the bot should automatically reconnect or not.
     */
    var isAutoReconnecting: Boolean
    /**
     * Whether the bot should wait for all servers to be loaded or not.
     *
     * This value is `true` by default.
     * If it's set to `false` the list of servers ([.getServers]) will be empty after connecting and
     * will be filled a few seconds later (depending on the amount of servers).
     */
    var isWaitingForServersOnStartup: Boolean
    /**
     * The size of the message cache.
     *
     * If the cache is full the oldest message in the cache will be removed.
     */
    var messageCacheSize: Int
    /**
     * The Discord user object that represents this bot.
     */
    val botUser: User
    /**
     * All servers known to the bot.
     */
    val servers: MappedCollection<CharSequence, Server>
    /**
     * All cached users known to the bot.
     *
     * Some members of bigger servers may not be in the cache because discord only sends the online users for servers
     * with more than 250 members.
     */
    val users: MappedCollection<CharSequence, User>
    /**
     * All channels known to the bot.
     */
    val channels: MappedCollection<CharSequence, Channel>
    /**
     * All voice channels known to the bot.
     */
    val voiceChannels: MappedCollection<CharSequence, VoiceChannel>
    /**
     * Gets the rate limit manager which caches all rate limits of this bot.
     */
    val rateLimitManager: RateLimitManager
    /**
     * The used thread pool of this bot.
     *
     * The [ThreadPool] contains the used thread pool(s) of this bot.
     * Don't use multi-threading if you don't know how to make things thread-safe
     * or how to prevent stuff like deadlocks!
     *
     * @return The used thread pool.
     */
    val threadPool: ThreadPool

    /**
     * Gets a user by its id. It first will check if the user is in the cache. If no user was found in the cache it
     * tries to request it from the Discord.
     *
     * @param userId The id of the user.
     * @return The user with the given id or null if no user with that id was found.
     */
    fun getUser(userId: CharSequence): Future<User>

    /**
     * Gets a message by its id.
     *
     * This function may return null even if the message exists!
     *
     * @param id The id of the message.
     * @return The message with the given id or null no message was found.
     */
    fun getMessage(messageId: CharSequence): Message?

    /**
     * Accepts an invite.
     *
     * @param inviteCode The invite code.
     * @param callback The callback which will be informed when you joined the server or joining failed.
     * @return The server.
     */
    fun acceptInvite(inviteCode: CharSequence, callback: FutureCallback<Server>? = null): Future<Server>

    /**
     * Creates a new server.
     *
     * @param name The name of the new server.
     * @param region The region of the server.
     * @param icon The icon of the server.
     * @param callback The callback which will be informed when you created the server.
     * @return The created server.
     */
    fun createServer(name: CharSequence, region: Region = Region.US_CENTRAL,
                     icon: BufferedImage? = null, callback: FutureCallback<Server>? = null): Future<Server>

    /**
     * Tries to parse the given invite.
     *
     * @param invite The invite code or the invite url.
     * @param callback The callback which will be informed when the invite has been parsed.
     * @return The parsed invite.
     */
    fun parseInvite(invite: CharSequence, callback: FutureCallback<Invite>? = null): Future<Invite>

    /**
     * Deletes the invite with the given code.
     *
     * @param inviteCode The invite code.
     * @return A future which tells us whether the deletion was successful or not.
     */
    fun deleteInvite(inviteCode: CharSequence): Future<Void>

    /**
     * Gets a new permissions builder which is either a copy of the given permissions if not null, or a new one with all
     * permissions set to [de.btobastian.javacord.entities.permissions.PermissionState.NONE]
     *
     * @param permissions The permissions which should be copied.
     * @return A new permissions builder.
     */
    fun createPermissionsBuilder(permissions: Permissions? = null): PermissionsBuilder
}