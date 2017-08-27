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

    override fun onReactionRemove(api: DiscordAPI, reaction: Reaction, user: User) {
        ReactionRemoveEvent(reaction, user).call(k)
    }

    override fun onReactionRemoveAll(api: DiscordAPI, message: Message, previousReactions: List<Reaction>) {
        ReactionRemoveAllEvent(message, previousReactions).call(k)
    }

    override fun onTypingStart(api: DiscordAPI, user: User, channel: Channel) {
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

    override fun onCustomEmojiDelete(api: DiscordAPI, p1: CustomEmoji?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerChangeIcon(api: DiscordAPI, p1: Server?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerChangeName(api: DiscordAPI, p1: Server?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerChangeOwner(api: DiscordAPI, p1: Server?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerChangeRegion(api: DiscordAPI, p1: Server?, p2: Region?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerJoin(api: DiscordAPI, p1: Server?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerLeave(api: DiscordAPI, p1: Server?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerMemberAdd(api: DiscordAPI, p1: User?, p2: Server?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerMemberBan(api: DiscordAPI, p1: User?, p2: Server?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerMemberRemove(api: DiscordAPI, p1: User?, p2: Server?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerMemberUnban(api: DiscordAPI, p1: String?, p2: Server?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserChangeGame(api: DiscordAPI, p1: User?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserChangeName(api: DiscordAPI, p1: User?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserChangeNickname(api: DiscordAPI, p1: Server?, p2: User?, p3: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserChangeStatus(api: DiscordAPI, p1: User?, p2: UserStatus?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserRoleAdd(api: DiscordAPI, p1: User?, p2: Role?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserRoleRemove(api: DiscordAPI, p1: User?, p2: Role?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserChangeOverwrittenPermissions(api: DiscordAPI, p1: User?, channel: Channel?, p3: Permissions?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserChangeOverwrittenPermissions(api: DiscordAPI, p1: User?, channel: VoiceChannel?, p3: Permissions?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserJoinVoiceChannel(api: DiscordAPI, p1: User?, channel: VoiceChannel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserLeaveVoiceChannel(api: DiscordAPI, p1: User?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onVoiceChannelChangeName(api: DiscordAPI, channel: VoiceChannel?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onVoiceChannelChangePosition(api: DiscordAPI, channel: VoiceChannel?, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onVoiceChannelCreate(api: DiscordAPI, channel: VoiceChannel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onVoiceChannelDelete(api: DiscordAPI, channel: VoiceChannel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}