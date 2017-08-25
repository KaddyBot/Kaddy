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
import de.btobastian.javacord.listener.channel.ChannelChangeNameListener
import de.btobastian.javacord.listener.channel.ChannelChangePositionListener
import de.btobastian.javacord.listener.channel.ChannelChangeTopicListener
import de.btobastian.javacord.listener.channel.ChannelCreateListener
import de.btobastian.javacord.listener.channel.ChannelDeleteListener
import de.btobastian.javacord.listener.message.MessageCreateListener
import de.btobastian.javacord.listener.message.MessageDeleteListener
import de.btobastian.javacord.listener.message.MessageEditListener
import de.btobastian.javacord.listener.message.ReactionAddListener
import de.btobastian.javacord.listener.message.ReactionRemoveAllListener
import de.btobastian.javacord.listener.message.ReactionRemoveListener
import de.btobastian.javacord.listener.message.TypingStartListener
import de.btobastian.javacord.listener.role.RoleChangeColorListener
import de.btobastian.javacord.listener.role.RoleChangeHoistListener
import de.btobastian.javacord.listener.role.RoleChangeManagedListener
import de.btobastian.javacord.listener.role.RoleChangeMentionableListener
import de.btobastian.javacord.listener.role.RoleChangeNameListener
import de.btobastian.javacord.listener.role.RoleChangePermissionsListener
import de.btobastian.javacord.listener.role.RoleChangePositionListener
import de.btobastian.javacord.listener.role.RoleCreateListener
import de.btobastian.javacord.listener.role.RoleDeleteListener
import de.btobastian.javacord.listener.role.RoleChangeOverwrittenPermissionsListener
import de.btobastian.javacord.listener.server.CustomEmojiDeleteListener
import de.btobastian.javacord.listener.server.ServerChangeIconListener
import de.btobastian.javacord.listener.server.ServerChangeNameListener
import de.btobastian.javacord.listener.server.ServerChangeOwnerListener
import de.btobastian.javacord.listener.server.ServerChangeRegionListener
import de.btobastian.javacord.listener.server.ServerJoinListener
import de.btobastian.javacord.listener.server.ServerLeaveListener
import de.btobastian.javacord.listener.server.ServerMemberAddListener
import de.btobastian.javacord.listener.server.ServerMemberBanListener
import de.btobastian.javacord.listener.server.ServerMemberRemoveListener
import de.btobastian.javacord.listener.server.ServerMemberUnbanListener
import de.btobastian.javacord.listener.user.UserChangeGameListener
import de.btobastian.javacord.listener.user.UserChangeNameListener
import de.btobastian.javacord.listener.user.UserChangeNicknameListener
import de.btobastian.javacord.listener.user.UserChangeStatusListener
import de.btobastian.javacord.listener.user.UserRoleAddListener
import de.btobastian.javacord.listener.user.UserRoleRemoveListener
import de.btobastian.javacord.listener.user.UserChangeOverwrittenPermissionsListener
import de.btobastian.javacord.listener.voice.UserJoinVoiceChannelListener
import de.btobastian.javacord.listener.voice.UserLeaveVoiceChannelListener
import de.btobastian.javacord.listener.voicechannel.VoiceChannelChangeNameListener
import de.btobastian.javacord.listener.voicechannel.VoiceChannelChangePositionListener
import de.btobastian.javacord.listener.voicechannel.VoiceChannelCreateListener
import de.btobastian.javacord.listener.voicechannel.VoiceChannelDeleteListener
import java.awt.Color

internal class AllListener(private val kaddy: Kaddy) :
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

    override fun onChannelChangeName(p0: DiscordAPI?, p1: Channel?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChannelChangePosition(p0: DiscordAPI?, p1: Channel?, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChannelChangeTopic(p0: DiscordAPI?, p1: Channel?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChannelCreate(p0: DiscordAPI?, p1: Channel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChannelDelete(p0: DiscordAPI?, p1: Channel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMessageCreate(p0: DiscordAPI?, p1: Message?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMessageDelete(p0: DiscordAPI?, p1: Message?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMessageEdit(p0: DiscordAPI?, p1: Message?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onReactionAdd(p0: DiscordAPI?, p1: Reaction?, p2: User?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onReactionRemove(p0: DiscordAPI?, p1: Reaction?, p2: User?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onReactionRemoveAll(p0: DiscordAPI?, p1: Message?, p2: MutableList<Reaction>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTypingStart(p0: DiscordAPI?, p1: User?, p2: Channel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoleChangeColor(p0: DiscordAPI?, p1: Role?, p2: Color?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoleChangeHoist(p0: DiscordAPI?, p1: Role?, p2: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoleChangeManaged(p0: DiscordAPI?, p1: Role?, p2: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoleChangeMentionable(p0: DiscordAPI?, p1: Role?, p2: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoleChangeName(p0: DiscordAPI?, p1: Role?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoleChangePermissions(p0: DiscordAPI?, p1: Role?, p2: Permissions?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoleChangePosition(p0: DiscordAPI?, p1: Role?, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoleCreate(p0: DiscordAPI?, p1: Role?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoleDelete(p0: DiscordAPI?, p1: Role?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoleChangeOverwrittenPermissions(p0: DiscordAPI?, p1: Role?, p2: Channel?, p3: Permissions?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRoleChangeOverwrittenPermissions(p0: DiscordAPI?, p1: Role?, p2: VoiceChannel?, p3: Permissions?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCustomEmojiDelete(p0: DiscordAPI?, p1: CustomEmoji?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerChangeIcon(p0: DiscordAPI?, p1: Server?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerChangeName(p0: DiscordAPI?, p1: Server?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerChangeOwner(p0: DiscordAPI?, p1: Server?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerChangeRegion(p0: DiscordAPI?, p1: Server?, p2: Region?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerJoin(p0: DiscordAPI?, p1: Server?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerLeave(p0: DiscordAPI?, p1: Server?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerMemberAdd(p0: DiscordAPI?, p1: User?, p2: Server?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerMemberBan(p0: DiscordAPI?, p1: User?, p2: Server?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerMemberRemove(p0: DiscordAPI?, p1: User?, p2: Server?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerMemberUnban(p0: DiscordAPI?, p1: String?, p2: Server?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserChangeGame(p0: DiscordAPI?, p1: User?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserChangeName(p0: DiscordAPI?, p1: User?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserChangeNickname(p0: DiscordAPI?, p1: Server?, p2: User?, p3: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserChangeStatus(p0: DiscordAPI?, p1: User?, p2: UserStatus?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserRoleAdd(p0: DiscordAPI?, p1: User?, p2: Role?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserRoleRemove(p0: DiscordAPI?, p1: User?, p2: Role?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserChangeOverwrittenPermissions(p0: DiscordAPI?, p1: User?, p2: Channel?, p3: Permissions?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserChangeOverwrittenPermissions(p0: DiscordAPI?, p1: User?, p2: VoiceChannel?, p3: Permissions?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserJoinVoiceChannel(p0: DiscordAPI?, p1: User?, p2: VoiceChannel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserLeaveVoiceChannel(p0: DiscordAPI?, p1: User?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onVoiceChannelChangeName(p0: DiscordAPI?, p1: VoiceChannel?, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onVoiceChannelChangePosition(p0: DiscordAPI?, p1: VoiceChannel?, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onVoiceChannelCreate(p0: DiscordAPI?, p1: VoiceChannel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onVoiceChannelDelete(p0: DiscordAPI?, p1: VoiceChannel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}