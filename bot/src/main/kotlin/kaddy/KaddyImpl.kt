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

import dtmlibs.logging.Loggable
import kaddy.util.KaddyLoggable
import net.dv8tion.jda.core.JDA
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

internal class KaddyImpl(internal val api: JDA) : Kaddy, Loggable by KaddyLoggable {

    override val ping: Long
        get() = api.ping
    override val presence: Presence = Presence(api.presence)
    override val responseTotal: Long
        get() = api.responseTotal
    override val selfUser: SelfUser
        get() = api.selfUser

    override fun createGuild(name: CharSequence): GuildAction
            = api.createGuild(name.toString())

    override val guilds = DefaultMappedCollection<Guild>({ api.guilds },
            { api.getGuildById(it) })

    override fun getGuilds(name: CharSequence, ignoreCase: Boolean):
            Collection<Guild>
            = api.getGuildsByName(name.toString(), ignoreCase)

    override fun getMutualGuilds(users: Collection<User>): Collection<Guild>
            = api.getMutualGuilds(users)

    override fun getMutualGuilds(vararg users: User): Collection<Guild> {
        return api.getMutualGuilds(*users)
    }

    override val users = DefaultMappedCollection<User>({ api.users },
            { api.getUserById(it) })

    override fun getUsers(name: CharSequence, ignoreCase: Boolean):
            Collection<User> = api.getUsersByName(name.toString(), ignoreCase)

    override fun retrieveUser(id: Long): RestAction<User>
            = api.retrieveUserById(id)

    override fun retrieveUser(id: CharSequence): RestAction<User>
            = api.retrieveUserById(id.toString())

    override val textChannels = DefaultMappedCollection<TextChannel>(
            { api.textChannels },
            { api.getTextChannelById(it) })

    override fun getTextChannels(name: CharSequence, ignoreCase: Boolean):
            Collection<TextChannel> = api.getTextChannelsByName(name.toString(),
            ignoreCase)

    override val voiceChannels = DefaultMappedCollection<VoiceChannel>(
            { api.voiceChannels },
            { api.getVoiceChannelById(it) })

    override fun getVoiceChannels(name: CharSequence, ignoreCase: Boolean):
            Collection<VoiceChannel>
            = api.getVoiceChannelByName(name.toString(), ignoreCase)

    override val privateChannels = DefaultMappedCollection<PrivateChannel>(
            { api.privateChannels },
            { api.getPrivateChannelById(it) })

    override val categories = DefaultMappedCollection<Category>(
            { api.categories },
            { api.getCategoryById(it) })

    override fun getCategories(name: CharSequence, ignoreCase: Boolean):
            Collection<Category>
            = api.getCategoriesByName(name.toString(), ignoreCase)

    override val emotes = DefaultMappedCollection<Emote>({ api.emotes },
            { api.getEmoteById(it) })

    override fun getEmotes(name: CharSequence, ignoreCase: Boolean):
            Collection<Emote> = api.getEmotesByName(name.toString(), ignoreCase)

    override val roles = DefaultMappedCollection<Role>({ api.roles },
            { api.getRoleById(it) })

    override fun getRoles(name: CharSequence, ignoreCase: Boolean):
            Collection<Role> = api.getRolesByName(name.toString(), ignoreCase)

    override val accountType: AccountType
        get() = api.accountType

//    override fun getMessage(messageId: CharSequence): Message? = api.getMessageById(messageId.toString())
//
//    override fun acceptInvite(inviteCode: CharSequence, callback: FutureCallback<Guild>?): Future<Guild>
//            = api.acceptInvite(inviteCode.toString(), callback)
//    override fun parseInvite(invite: CharSequence, callback: FutureCallback<Invite>?): Future<Invite>
//            = api.parseInvite(invite.toString(), callback)
//
//    override fun deleteInvite(inviteCode: CharSequence): Future<Void> = api.deleteInvite(inviteCode.toString())
}