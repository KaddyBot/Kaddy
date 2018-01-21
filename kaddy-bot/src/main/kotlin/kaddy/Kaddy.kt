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

import dtmlibs.logging.LogOwner
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.entities.Category
import net.dv8tion.jda.core.entities.Emote
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.PrivateChannel
import net.dv8tion.jda.core.entities.Role
import net.dv8tion.jda.core.entities.SelfUser
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.entities.VoiceChannel
import net.dv8tion.jda.core.requests.RestAction
import net.dv8tion.jda.core.requests.restaction.GuildAction

interface Kaddy : LogOwner {

    fun createGuild(name: CharSequence): GuildAction

    val accountType: AccountType

    val categories: IDMappedCollection<Category>

    fun getCategories(name: CharSequence, ignoreCase: Boolean = true): Collection<Category>

    val emotes: IDMappedCollection<Emote>

    fun getEmotes(name: CharSequence, ignoreCase: Boolean = true): Collection<Emote>

    /**
     * All guilds (servers) known to the bot.
     */
    val guilds: IDMappedCollection<Guild>

    fun getGuilds(name: CharSequence, ignoreCase: Boolean = true): Collection<Guild>

    fun getMutualGuilds(users: Collection<User>): Collection<Guild>

    fun getMutualGuilds(vararg users: User): Collection<Guild>

    val ping: Long

    val presence: Presence

    val privateChannels: IDMappedCollection<PrivateChannel>

    val responseTotal: Long

    val roles: IDMappedCollection<Role>

    fun getRoles(name: CharSequence, ignoreCase: Boolean = true): Collection<Role>

    val selfUser: SelfUser

    /**
     * All text channels known to the bot.
     */
    val textChannels: IDMappedCollection<TextChannel>

    fun getTextChannels(name: CharSequence, ignoreCase: Boolean = true): Collection<TextChannel>

    /**
     * Gets a user by its id. It first will check if the user is in the cache. If no user was found in the cache it
     * tries to request it from the Discord.
     *
     * @param userId The id of the user.
     * @return The user with the given id or null if no user with that id was found.
     */

    /**
     * All cached users known to the bot.
     *
     * Some members of bigger servers may not be in the cache because discord only sends the online users for servers
     * with more than 250 members.
     */
    val users: IDMappedCollection<User>

    fun getUsers(name: CharSequence, ignoreCase: Boolean = true): Collection<User>

    fun retrieveUser(id: Long): RestAction<User>

    fun retrieveUser(id: CharSequence): RestAction<User>

    /**
     * All voice channels known to the bot.
     */
    val voiceChannels: IDMappedCollection<VoiceChannel>

    fun getVoiceChannels(name: CharSequence, ignoreCase: Boolean = true): Collection<VoiceChannel>

//    /**
//     * Gets a message by its id.
//     *
//     * This function may return null even if the message exists!
//     *
//     * @param id The id of the message.
//     * @return The message with the given id or null no message was found.
//     */
//    fun getMessage(messageId: CharSequence): Message?
//
//    /**
//     * Accepts an invite.
//     *
//     * @param inviteCode The invite code.
//     * @param callback The callback which will be informed when you joined the server or joining failed.
//     * @return The server.
//     */
//    fun acceptInvite(inviteCode: CharSequence, callback: FutureCallback<Guild>? = null): Future<Guild>
//
//    /**
//     * Tries to parse the given invite.
//     *
//     * @param invite The invite code or the invite url.
//     * @param callback The callback which will be informed when the invite has been parsed.
//     * @return The parsed invite.
//     */
//    fun parseInvite(invite: CharSequence, callback: FutureCallback<Invite>? = null): Future<Invite>
//
//    /**
//     * Deletes the invite with the given code.
//     *
//     * @param inviteCode The invite code.
//     * @return A future which tells us whether the deletion was successful or not.
//     */
//    fun deleteInvite(inviteCode: CharSequence): Future<Void>
}