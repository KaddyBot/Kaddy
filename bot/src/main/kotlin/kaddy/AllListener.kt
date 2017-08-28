package kaddy

import de.btobastian.javacord.DiscordAPI
import de.btobastian.javacord.entities.Channel
import de.btobastian.javacord.entities.CustomEmoji
import de.btobastian.javacord.entities.Region
import de.btobastian.javacord.entities.Server
import de.btobastian.javacord.entities.User
import de.btobastian.javacord.entities.UserStatus
import de.btobastian.javacord.entities.VoiceChannel
import de.btobastian.javacord.entities.message.Message
import de.btobastian.javacord.entities.message.Reaction
import de.btobastian.javacord.entities.permissions.Permissions
import de.btobastian.javacord.entities.permissions.Role
import de.btobastian.javacord.listener.channel.*
import de.btobastian.javacord.listener.message.*
import de.btobastian.javacord.listener.role.*
import de.btobastian.javacord.listener.server.*
import de.btobastian.javacord.listener.user.*
import de.btobastian.javacord.listener.voice.*
import de.btobastian.javacord.listener.voicechannel.*
import kaddy.event.channel.*
import kaddy.event.message.*
import kaddy.event.role.*
import kaddy.event.server.CustomEmojiDeleteEvent
import kaddy.event.server.ServerChangeIconEvent
import kaddy.event.server.ServerChangeNameEvent
import kaddy.event.server.ServerChangeOwnerEvent
import kaddy.event.server.ServerChangeRegionEvent
import kaddy.event.server.ServerJoinEvent
import kaddy.event.server.ServerLeaveEvent
import kaddy.event.server.ServerMemberAddEvent
import kaddy.event.server.ServerMemberBanEvent
import kaddy.event.server.ServerMemberRemoveEvent
import kaddy.event.server.ServerMemberUnbanEvent
import kaddy.event.user.UserChangeGameEvent
import kaddy.event.user.UserChangeNameEvent
import kaddy.event.user.UserChangeNicknameEvent
import kaddy.event.user.UserChangeOverwrittenPermissionsEvent
import kaddy.event.user.UserChangeOverwrittenVoicePermissionsEvent
import kaddy.event.user.UserChangeStatusEvent
import kaddy.event.user.UserRoleAddEvent
import kaddy.event.user.UserRoleRemoveEvent
import kaddy.event.voicechannel.UserJoinVoiceChannelEvent
import kaddy.event.voicechannel.UserLeaveVoiceChannelEvent
import kaddy.event.voicechannel.VoiceChannelChangeNameEvent
import kaddy.event.voicechannel.VoiceChannelChangePositionEvent
import kaddy.event.voicechannel.VoiceChannelCreateEvent
import kaddy.event.voicechannel.VoiceChannelDeleteEvent
import java.awt.Color

internal class AllListener(private val k: Kaddy) :
        ChannelChangeNameListener, ChannelChangePositionListener, ChannelChangeTopicListener, ChannelCreateListener,
        ChannelDeleteListener, MessageCreateListener, MessageDeleteListener, MessageEditListener, ReactionAddListener,
        ReactionRemoveListener, ReactionRemoveAllListener, TypingStartListener, RoleChangeColorListener,
        RoleChangeHoistListener, RoleChangeManagedListener, RoleChangeMentionableListener, RoleChangeNameListener,
        RoleChangePermissionsListener, RoleChangePositionListener, RoleCreateListener, RoleDeleteListener,
        RoleChangeOverwrittenPermissionsListener, CustomEmojiDeleteListener, ServerChangeIconListener,
        ServerChangeNameListener, ServerChangeOwnerListener, ServerChangeRegionListener, ServerJoinListener,
        ServerLeaveListener, ServerMemberAddListener, ServerMemberBanListener, ServerMemberRemoveListener,
        ServerMemberUnbanListener, UserChangeGameListener, UserChangeNameListener, UserChangeNicknameListener,
        UserChangeStatusListener, UserRoleAddListener, UserRoleRemoveListener, UserChangeOverwrittenPermissionsListener,
        UserJoinVoiceChannelListener, UserLeaveVoiceChannelListener, VoiceChannelChangeNameListener,
        VoiceChannelChangePositionListener, VoiceChannelCreateListener, VoiceChannelDeleteListener {

    override fun onChannelChangeName(api: DiscordAPI, channel: Channel, previousName: String) {
        ChannelChangeNameEvent(channel, previousName).call(k)
    }

    override fun onChannelChangePosition(api: DiscordAPI, channel: Channel, previousPosition: Int) {
        ChannelChangePositionEvent(channel, previousPosition).call(k)
    }

    override fun onChannelChangeTopic(api: DiscordAPI, channel: Channel, previousTopic: String?) {
        ChannelChangeTopicEvent(channel, previousTopic).call(k)
    }

    override fun onChannelCreate(api: DiscordAPI, channel: Channel) {
        ChannelCreateEvent(channel).call(k)
    }

    override fun onChannelDelete(api: DiscordAPI, channel: Channel) {
        ChannelDeleteEvent(channel).call(k)
    }

    override fun onMessageCreate(api: DiscordAPI, message: Message) {
        MessageCreateEvent(message).call(k)
    }

    override fun onMessageDelete(api: DiscordAPI, message: Message) {
        MessageDeleteEvent(message).call(k)
    }

    override fun onMessageEdit(api: DiscordAPI, message: Message, previousContent: String) {
        MessageEditEvent(message, previousContent).call(k)
    }

    override fun onReactionAdd(api: DiscordAPI, reaction: Reaction, user: User) {
        ReactionAddEvent(reaction, user).call(k)
    }

    override fun onReactionRemove(api: DiscordAPI, reaction: Reaction?, user: User) {
        ReactionRemoveEvent(reaction, user).call(k)
    }

    override fun onReactionRemoveAll(api: DiscordAPI, message: Message, previousReactions: List<Reaction>) {
        ReactionRemoveAllEvent(message, previousReactions).call(k)
    }

    override fun onTypingStart(api: DiscordAPI, user: User, channel: Channel?) {
        TypingStartEvent(user, channel).call(k)
    }

    override fun onRoleChangeColor(api: DiscordAPI, role: Role, previousColor: Color) {
        RoleChangeColorEvent(role, previousColor).call(k)
    }

    override fun onRoleChangeHoist(api: DiscordAPI, role: Role, previouslyHoisted: Boolean) {
        RoleChangeHoistedEvent(role, previouslyHoisted).call(k)
    }

    override fun onRoleChangeManaged(api: DiscordAPI, role: Role, previouslyManaged: Boolean) {
        RoleChangeManagedEvent(role, previouslyManaged).call(k)
    }

    override fun onRoleChangeMentionable(api: DiscordAPI, role: Role, previouslyMentionable: Boolean) {
        RoleChangeMentionableEvent(role, previouslyMentionable).call(k)
    }

    override fun onRoleChangeName(api: DiscordAPI, role: Role, previousName: String) {
        RoleChangeNameEvent(role, previousName).call(k)
    }

    override fun onRoleChangePermissions(api: DiscordAPI, role: Role, previousPermissions: Permissions) {
        RoleChangePermissionsEvent(role, previousPermissions).call(k)
    }

    override fun onRoleChangePosition(api: DiscordAPI, role: Role, previousPosition: Int) {
        RoleChangePositionEvent(role, previousPosition).call(k)
    }

    override fun onRoleCreate(api: DiscordAPI, role: Role) {
        RoleCreateEvent(role).call(k)
    }

    override fun onRoleDelete(api: DiscordAPI, role: Role) {
        RoleDeleteEvent(role).call(k)
    }

    override fun onRoleChangeOverwrittenPermissions(api: DiscordAPI, role: Role, channel: Channel,
                                                    previousPermissions: Permissions) {
        RoleChangeOverwrittenPermissionsEvent(role, channel, previousPermissions).call(k)
    }

    override fun onRoleChangeOverwrittenPermissions(api: DiscordAPI, role: Role, channel: VoiceChannel,
                                                    previousPermissions: Permissions) {
        RoleChangeOverwrittenPermissionsVoiceEvent(role, channel, previousPermissions).call(k)
    }

    override fun onCustomEmojiDelete(api: DiscordAPI, deletedEmoji: CustomEmoji) {
        CustomEmojiDeleteEvent(deletedEmoji).call(k)
    }

    override fun onServerChangeIcon(api: DiscordAPI, server: Server, previousIcon: String?) {
        ServerChangeIconEvent(server, previousIcon).call(k)
    }

    override fun onServerChangeName(api: DiscordAPI, server: Server, previousName: String) {
        ServerChangeNameEvent(server, previousName).call(k)
    }

    override fun onServerChangeOwner(api: DiscordAPI, server: Server, previousOwnerId: String) {
        ServerChangeOwnerEvent(server, previousOwnerId).call(k)
    }

    override fun onServerChangeRegion(api: DiscordAPI, server: Server, previousRegion: Region) {
        ServerChangeRegionEvent(server, previousRegion).call(k)
    }

    override fun onServerJoin(api: DiscordAPI, server: Server) {
        ServerJoinEvent(server).call(k)
    }

    override fun onServerLeave(api: DiscordAPI, server: Server) {
        ServerLeaveEvent(server).call(k)
    }

    override fun onServerMemberAdd(api: DiscordAPI, user: User, server: Server) {
        ServerMemberAddEvent(server, user).call(k)
    }

    override fun onServerMemberBan(api: DiscordAPI, user: User, server: Server) {
        ServerMemberBanEvent(server, user).call(k)
    }

    override fun onServerMemberRemove(api: DiscordAPI, user: User, server: Server) {
        ServerMemberRemoveEvent(server, user).call(k)
    }

    override fun onServerMemberUnban(api: DiscordAPI, userId: String, server: Server) {
        ServerMemberUnbanEvent(server, userId).call(k)
    }

    override fun onUserChangeGame(api: DiscordAPI, user: User, previousGame: String?) {
        UserChangeGameEvent(user, previousGame).call(k)
    }

    override fun onUserChangeName(api: DiscordAPI, user: User, previousName: String) {
        UserChangeNameEvent(user, previousName).call(k)
    }

    override fun onUserChangeNickname(api: DiscordAPI, server: Server, user: User, previousNickname: String?) {
        UserChangeNicknameEvent(user, server, previousNickname).call(k)
    }

    override fun onUserChangeStatus(api: DiscordAPI, user: User, previousStatus: UserStatus) {
        UserChangeStatusEvent(user, previousStatus).call(k)
    }

    override fun onUserRoleAdd(api: DiscordAPI, user: User, addedRole: Role) {
        UserRoleAddEvent(user, addedRole).call(k)
    }

    override fun onUserRoleRemove(api: DiscordAPI, user: User, removedRole: Role) {
        UserRoleRemoveEvent(user, removedRole).call(k)
    }

    override fun onUserChangeOverwrittenPermissions(api: DiscordAPI, user: User, channel: Channel,
                                                    previousPermissions: Permissions) {
        UserChangeOverwrittenPermissionsEvent(user, channel, previousPermissions).call(k)
    }

    override fun onUserChangeOverwrittenPermissions(api: DiscordAPI, user: User, channel: VoiceChannel,
                                                    previousPermissions: Permissions) {
        UserChangeOverwrittenVoicePermissionsEvent(user, channel, previousPermissions).call(k)
    }

    override fun onUserJoinVoiceChannel(api: DiscordAPI, user: User?, channel: VoiceChannel) {
        UserJoinVoiceChannelEvent(channel, user).call(k)
    }

    override fun onUserLeaveVoiceChannel(api: DiscordAPI, user: User?) {
        UserLeaveVoiceChannelEvent(user).call(k)
    }

    override fun onVoiceChannelChangeName(api: DiscordAPI, channel: VoiceChannel, previousName: String) {
        VoiceChannelChangeNameEvent(channel, previousName).call(k)
    }

    override fun onVoiceChannelChangePosition(api: DiscordAPI, channel: VoiceChannel, previousPosition: Int) {
        VoiceChannelChangePositionEvent(channel, previousPosition).call(k)
    }

    override fun onVoiceChannelCreate(api: DiscordAPI, channel: VoiceChannel) {
        VoiceChannelCreateEvent(channel).call(k)
    }

    override fun onVoiceChannelDelete(api: DiscordAPI, channel: VoiceChannel) {
        VoiceChannelDeleteEvent(channel).call(k)
    }
}