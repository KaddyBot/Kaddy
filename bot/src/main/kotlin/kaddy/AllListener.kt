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

import kaddy.event.channel.*
import kaddy.event.message.*
import kaddy.event.message.MessageDeleteEvent
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
import kaddy.event.user.UserTypingEvent
import kaddy.event.voicechannel.UserJoinVoiceChannelEvent
import kaddy.event.voicechannel.UserLeaveVoiceChannelEvent
import kaddy.event.voicechannel.VoiceChannelChangeNameEvent
import kaddy.event.voicechannel.VoiceChannelChangePositionEvent
import kaddy.event.voicechannel.VoiceChannelCreateEvent
import kaddy.event.voicechannel.VoiceChannelDeleteEvent
import net.dv8tion.jda.client.events.call.CallCreateEvent
import net.dv8tion.jda.client.events.call.CallDeleteEvent
import net.dv8tion.jda.client.events.call.GenericCallEvent
import net.dv8tion.jda.client.events.call.update.CallUpdateRegionEvent
import net.dv8tion.jda.client.events.call.update.CallUpdateRingingUsersEvent
import net.dv8tion.jda.client.events.call.update.GenericCallUpdateEvent
import net.dv8tion.jda.client.events.call.voice.*
import net.dv8tion.jda.client.events.group.*
import net.dv8tion.jda.client.events.group.update.GenericGroupUpdateEvent
import net.dv8tion.jda.client.events.group.update.GroupUpdateIconEvent
import net.dv8tion.jda.client.events.group.update.GroupUpdateNameEvent
import net.dv8tion.jda.client.events.group.update.GroupUpdateOwnerEvent
import net.dv8tion.jda.client.events.message.group.*
import net.dv8tion.jda.client.events.message.group.react.GenericGroupMessageReactionEvent
import net.dv8tion.jda.client.events.message.group.react.GroupMessageReactionAddEvent
import net.dv8tion.jda.client.events.message.group.react.GroupMessageReactionRemoveAllEvent
import net.dv8tion.jda.client.events.message.group.react.GroupMessageReactionRemoveEvent
import net.dv8tion.jda.client.events.relationship.*
import net.dv8tion.jda.core.events.*
import net.dv8tion.jda.core.events.channel.category.CategoryCreateEvent
import net.dv8tion.jda.core.events.channel.category.CategoryDeleteEvent
import net.dv8tion.jda.core.events.channel.category.GenericCategoryEvent
import net.dv8tion.jda.core.events.channel.category.update.CategoryUpdateNameEvent
import net.dv8tion.jda.core.events.channel.category.update.CategoryUpdatePermissionsEvent
import net.dv8tion.jda.core.events.channel.category.update.CategoryUpdatePositionEvent
import net.dv8tion.jda.core.events.channel.category.update.GenericCategoryUpdateEvent
import net.dv8tion.jda.core.events.channel.priv.PrivateChannelCreateEvent
import net.dv8tion.jda.core.events.channel.priv.PrivateChannelDeleteEvent
import net.dv8tion.jda.core.events.channel.text.GenericTextChannelEvent
import net.dv8tion.jda.core.events.channel.text.TextChannelCreateEvent
import net.dv8tion.jda.core.events.channel.text.TextChannelDeleteEvent
import net.dv8tion.jda.core.events.channel.text.update.*
import net.dv8tion.jda.core.events.channel.voice.GenericVoiceChannelEvent
import net.dv8tion.jda.core.events.channel.voice.update.*
import net.dv8tion.jda.core.events.emote.EmoteAddedEvent
import net.dv8tion.jda.core.events.emote.EmoteRemovedEvent
import net.dv8tion.jda.core.events.emote.GenericEmoteEvent
import net.dv8tion.jda.core.events.emote.update.EmoteUpdateNameEvent
import net.dv8tion.jda.core.events.emote.update.EmoteUpdateRolesEvent
import net.dv8tion.jda.core.events.emote.update.GenericEmoteUpdateEvent
import net.dv8tion.jda.core.events.guild.*
import net.dv8tion.jda.core.events.guild.member.*
import net.dv8tion.jda.core.events.guild.update.*
import net.dv8tion.jda.core.events.guild.voice.*
import net.dv8tion.jda.core.events.http.HttpRequestEvent
import net.dv8tion.jda.core.events.message.*
import net.dv8tion.jda.core.events.message.guild.*
import net.dv8tion.jda.core.events.message.guild.react.GenericGuildMessageReactionEvent
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionAddEvent
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionRemoveAllEvent
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionRemoveEvent
import net.dv8tion.jda.core.events.message.priv.*
import net.dv8tion.jda.core.events.message.priv.react.GenericPrivateMessageReactionEvent
import net.dv8tion.jda.core.events.message.priv.react.PrivateMessageReactionAddEvent
import net.dv8tion.jda.core.events.message.priv.react.PrivateMessageReactionRemoveAllEvent
import net.dv8tion.jda.core.events.message.priv.react.PrivateMessageReactionRemoveEvent
import net.dv8tion.jda.core.events.message.react.GenericMessageReactionEvent
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveAllEvent
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent
import net.dv8tion.jda.core.events.role.GenericRoleEvent
import net.dv8tion.jda.core.events.role.update.*
import net.dv8tion.jda.core.events.self.*
import net.dv8tion.jda.core.events.user.*
import net.dv8tion.jda.core.hooks.ListenerAdapter

internal class AllListener(private val k: Kaddy) : ListenerAdapter() {

    override fun onMessageDelete(event: net.dv8tion.jda.core.events.message.MessageDeleteEvent) {
        MessageDeleteEvent(event.messageIdLong, event.channel).call(k)
    }

    override fun onRoleCreate(event: net.dv8tion.jda.core.events.role.RoleCreateEvent) {
        RoleCreateEvent(event.role).call(k)
    }

    override fun onRoleDelete(event: net.dv8tion.jda.core.events.role.RoleDeleteEvent) {
        RoleDeleteEvent(event.role).call(k)
    }

    override fun onVoiceChannelCreate(event: net.dv8tion.jda.core.events.channel.voice.VoiceChannelCreateEvent) {
        VoiceChannelCreateEvent(event.channel).call(k)
    }

    override fun onVoiceChannelDelete(event: net.dv8tion.jda.core.events.channel.voice.VoiceChannelDeleteEvent) {
        VoiceChannelDeleteEvent(event.channel).call(k)
    }

    override fun onGenericTextChannel(event: GenericTextChannelEvent?) {
        super.onGenericTextChannel(event)
    }

    override fun onUserTyping(event: net.dv8tion.jda.core.events.user.UserTypingEvent) {
        UserTypingEvent(event.user, event.channel, event.timestamp).call(k)
    }

    override fun onGuildVoiceGuildMute(event: GuildVoiceGuildMuteEvent?) {
        super.onGuildVoiceGuildMute(event)
    }

    override fun onShutdown(event: ShutdownEvent?) {
        super.onShutdown(event)
    }

    override fun onGenericPrivateMessage(event: GenericPrivateMessageEvent?) {
        super.onGenericPrivateMessage(event)
    }

    override fun onCategoryUpdateName(event: CategoryUpdateNameEvent?) {
        super.onCategoryUpdateName(event)
    }

    override fun onEmoteAdded(event: EmoteAddedEvent?) {
        super.onEmoteAdded(event)
    }

    override fun onSelfUpdateAvatar(event: SelfUpdateAvatarEvent?) {
        super.onSelfUpdateAvatar(event)
    }

    override fun onCallVoiceSelfMute(event: CallVoiceSelfMuteEvent?) {
        super.onCallVoiceSelfMute(event)
    }

    override fun onGuildLeave(event: GuildLeaveEvent?) {
        super.onGuildLeave(event)
    }

    override fun onCategoryUpdatePermissions(event: CategoryUpdatePermissionsEvent?) {
        super.onCategoryUpdatePermissions(event)
    }

    override fun onEmoteUpdateRoles(event: EmoteUpdateRolesEvent?) {
        super.onEmoteUpdateRoles(event)
    }

    override fun onGenericMessageReaction(event: GenericMessageReactionEvent?) {
        super.onGenericMessageReaction(event)
    }

    override fun onPrivateMessageEmbed(event: PrivateMessageEmbedEvent?) {
        super.onPrivateMessageEmbed(event)
    }

    override fun onException(event: ExceptionEvent?) {
        super.onException(event)
    }

    override fun onGenericSelfUpdate(event: GenericSelfUpdateEvent?) {
        super.onGenericSelfUpdate(event)
    }

    override fun onGenericEmoteUpdate(event: GenericEmoteUpdateEvent?) {
        super.onGenericEmoteUpdate(event)
    }

    override fun onDisconnect(event: DisconnectEvent?) {
        super.onDisconnect(event)
    }

    override fun onGenericCallUpdate(event: GenericCallUpdateEvent?) {
        super.onGenericCallUpdate(event)
    }

    override fun onGenericPrivateMessageReaction(event: GenericPrivateMessageReactionEvent?) {
        super.onGenericPrivateMessageReaction(event)
    }

    override fun onUserBlocked(event: UserBlockedEvent?) {
        super.onUserBlocked(event)
    }

    override fun onGroupMessageReactionRemove(event: GroupMessageReactionRemoveEvent?) {
        super.onGroupMessageReactionRemove(event)
    }

    override fun onGuildMessageReactionAdd(event: GuildMessageReactionAddEvent?) {
        super.onGuildMessageReactionAdd(event)
    }

    override fun onPrivateChannelCreate(event: PrivateChannelCreateEvent?) {
        super.onPrivateChannelCreate(event)
    }

    override fun onSelfUpdateEmail(event: SelfUpdateEmailEvent?) {
        super.onSelfUpdateEmail(event)
    }

    override fun onGroupLeave(event: GroupLeaveEvent?) {
        super.onGroupLeave(event)
    }

    override fun onGenericTextChannelUpdate(event: GenericTextChannelUpdateEvent?) {
        super.onGenericTextChannelUpdate(event)
    }

    override fun onFriendRequestIgnored(event: FriendRequestIgnoredEvent?) {
        super.onFriendRequestIgnored(event)
    }

    override fun onRoleUpdateMentionable(event: RoleUpdateMentionableEvent?) {
        super.onRoleUpdateMentionable(event)
    }

    override fun onEmoteUpdateName(event: EmoteUpdateNameEvent?) {
        super.onEmoteUpdateName(event)
    }

    override fun onGenericVoiceChannel(event: GenericVoiceChannelEvent?) {
        super.onGenericVoiceChannel(event)
    }

    override fun onRoleUpdateName(event: RoleUpdateNameEvent?) {
        super.onRoleUpdateName(event)
    }

    override fun onFriendRemoved(event: FriendRemovedEvent?) {
        super.onFriendRemoved(event)
    }

    override fun onGroupMessageReactionRemoveAll(event: GroupMessageReactionRemoveAllEvent?) {
        super.onGroupMessageReactionRemoveAll(event)
    }

    override fun onGuildUnban(event: GuildUnbanEvent?) {
        super.onGuildUnban(event)
    }

    override fun onGuildUpdateAfkChannel(event: GuildUpdateAfkChannelEvent?) {
        super.onGuildUpdateAfkChannel(event)
    }

    override fun onUserUnblocked(event: UserUnblockedEvent?) {
        super.onUserUnblocked(event)
    }

    override fun onGroupUserLeave(event: GroupUserLeaveEvent?) {
        super.onGroupUserLeave(event)
    }

    override fun onGuildMessageEmbed(event: GuildMessageEmbedEvent?) {
        super.onGuildMessageEmbed(event)
    }

    override fun onGenericUserPresence(event: GenericUserPresenceEvent?) {
        super.onGenericUserPresence(event)
    }

    override fun onTextChannelUpdateNSFW(event: TextChannelUpdateNSFWEvent?) {
        super.onTextChannelUpdateNSFW(event)
    }

    override fun onCallUpdateRegion(event: CallUpdateRegionEvent?) {
        super.onCallUpdateRegion(event)
    }

    override fun onGuildUpdateRegion(event: GuildUpdateRegionEvent?) {
        super.onGuildUpdateRegion(event)
    }

    override fun onRoleUpdateColor(event: RoleUpdateColorEvent?) {
        super.onRoleUpdateColor(event)
    }

    override fun onGenericVoiceChannelUpdate(event: GenericVoiceChannelUpdateEvent?) {
        super.onGenericVoiceChannelUpdate(event)
    }

    override fun onGuildMessageReactionRemoveAll(event: GuildMessageReactionRemoveAllEvent?) {
        super.onGuildMessageReactionRemoveAll(event)
    }

    override fun onGenericMessage(event: GenericMessageEvent?) {
        super.onGenericMessage(event)
    }

    override fun onUserGameUpdate(event: UserGameUpdateEvent?) {
        super.onUserGameUpdate(event)
    }

    override fun onGenericGuildMessage(event: GenericGuildMessageEvent?) {
        super.onGenericGuildMessage(event)
    }

    override fun onReady(event: ReadyEvent?) {
        super.onReady(event)
    }

    override fun onGenericGuild(event: GenericGuildEvent?) {
        super.onGenericGuild(event)
    }

    override fun onGenericGroupMessageReaction(event: GenericGroupMessageReactionEvent?) {
        super.onGenericGroupMessageReaction(event)
    }

    override fun onGenericCategoryUpdate(event: GenericCategoryUpdateEvent?) {
        super.onGenericCategoryUpdate(event)
    }

    override fun onGroupMessageReceived(event: GroupMessageReceivedEvent?) {
        super.onGroupMessageReceived(event)
    }

    override fun onCallUpdateRingingUsers(event: CallUpdateRingingUsersEvent?) {
        super.onCallUpdateRingingUsers(event)
    }

    override fun onGenericEmote(event: GenericEmoteEvent?) {
        super.onGenericEmote(event)
    }

    override fun onCallDelete(event: CallDeleteEvent?) {
        super.onCallDelete(event)
    }

    override fun onGenericCall(event: GenericCallEvent?) {
        super.onGenericCall(event)
    }

    override fun onCategoryCreate(event: CategoryCreateEvent?) {
        super.onCategoryCreate(event)
    }

    override fun onGuildVoiceDeafen(event: GuildVoiceDeafenEvent?) {
        super.onGuildVoiceDeafen(event)
    }

    override fun onStatusChange(event: StatusChangeEvent?) {
        super.onStatusChange(event)
    }

    override fun onTextChannelUpdatePosition(event: TextChannelUpdatePositionEvent?) {
        super.onTextChannelUpdatePosition(event)
    }

    override fun onGuildMessageDelete(event: GuildMessageDeleteEvent?) {
        super.onGuildMessageDelete(event)
    }

    override fun onGuildUpdateSystemChannel(event: GuildUpdateSystemChannelEvent?) {
        super.onGuildUpdateSystemChannel(event)
    }

    override fun onCategoryDelete(event: CategoryDeleteEvent?) {
        super.onCategoryDelete(event)
    }

    override fun onGenericGuildMember(event: GenericGuildMemberEvent?) {
        super.onGenericGuildMember(event)
    }

    override fun onGroupUpdateOwner(event: GroupUpdateOwnerEvent?) {
        super.onGroupUpdateOwner(event)
    }

    override fun onVoiceChannelUpdateBitrate(event: VoiceChannelUpdateBitrateEvent?) {
        super.onVoiceChannelUpdateBitrate(event)
    }

    override fun onGuildUpdateOwner(event: GuildUpdateOwnerEvent?) {
        super.onGuildUpdateOwner(event)
    }

    override fun onGuildUnavailable(event: GuildUnavailableEvent?) {
        super.onGuildUnavailable(event)
    }

    override fun onFriendRequestCanceled(event: FriendRequestCanceledEvent?) {
        super.onFriendRequestCanceled(event)
    }

    override fun onReconnect(event: ReconnectedEvent?) {
        super.onReconnect(event)
    }

    override fun onMessageReceived(event: MessageReceivedEvent?) {
        super.onMessageReceived(event)
    }

    override fun onRoleUpdatePosition(event: RoleUpdatePositionEvent?) {
        super.onRoleUpdatePosition(event)
    }

    override fun onTextChannelUpdateName(event: TextChannelUpdateNameEvent?) {
        super.onTextChannelUpdateName(event)
    }

    override fun onGenericGuildUpdate(event: GenericGuildUpdateEvent?) {
        super.onGenericGuildUpdate(event)
    }

    override fun onFriendRequestReceived(event: FriendRequestReceivedEvent?) {
        super.onFriendRequestReceived(event)
    }

    override fun onGuildUpdateVerificationLevel(event: GuildUpdateVerificationLevelEvent?) {
        super.onGuildUpdateVerificationLevel(event)
    }

    override fun onRoleUpdatePermissions(event: RoleUpdatePermissionsEvent?) {
        super.onRoleUpdatePermissions(event)
    }

    override fun onGenericUser(event: GenericUserEvent?) {
        super.onGenericUser(event)
    }

    override fun onGuildVoiceMute(event: GuildVoiceMuteEvent?) {
        super.onGuildVoiceMute(event)
    }

    override fun onGroupJoin(event: GroupJoinEvent?) {
        super.onGroupJoin(event)
    }

    override fun onPrivateMessageUpdate(event: PrivateMessageUpdateEvent?) {
        super.onPrivateMessageUpdate(event)
    }

    override fun onRoleUpdateHoisted(event: RoleUpdateHoistedEvent?) {
        super.onRoleUpdateHoisted(event)
    }

    override fun onGroupUpdateIcon(event: GroupUpdateIconEvent?) {
        super.onGroupUpdateIcon(event)
    }

    override fun onVoiceChannelUpdatePermissions(event: VoiceChannelUpdatePermissionsEvent?) {
        super.onVoiceChannelUpdatePermissions(event)
    }

    override fun onGuildUpdateName(event: GuildUpdateNameEvent?) {
        super.onGuildUpdateName(event)
    }

    override fun onGuildUpdateNotificationLevel(event: GuildUpdateNotificationLevelEvent?) {
        super.onGuildUpdateNotificationLevel(event)
    }

    override fun onUserOnlineStatusUpdate(event: UserOnlineStatusUpdateEvent?) {
        super.onUserOnlineStatusUpdate(event)
    }

    override fun onGenericRelationshipRemove(event: GenericRelationshipRemoveEvent?) {
        super.onGenericRelationshipRemove(event)
    }

    override fun onGuildJoin(event: GuildJoinEvent?) {
        super.onGuildJoin(event)
    }

    override fun onGenericGroupUpdate(event: GenericGroupUpdateEvent?) {
        super.onGenericGroupUpdate(event)
    }

    override fun onMessageEmbed(event: MessageEmbedEvent?) {
        super.onMessageEmbed(event)
    }

    override fun onFriendRequestSent(event: FriendRequestSentEvent?) {
        super.onFriendRequestSent(event)
    }

    override fun onEmoteRemoved(event: EmoteRemovedEvent?) {
        super.onEmoteRemoved(event)
    }

    override fun onResume(event: ResumedEvent?) {
        super.onResume(event)
    }

    override fun onGuildVoiceSelfMute(event: GuildVoiceSelfMuteEvent?) {
        super.onGuildVoiceSelfMute(event)
    }

    override fun onGroupMessageDelete(event: GroupMessageDeleteEvent?) {
        super.onGroupMessageDelete(event)
    }

    override fun onGuildUpdateExplicitContentLevel(event: GuildUpdateExplicitContentLevelEvent?) {
        super.onGuildUpdateExplicitContentLevel(event)
    }

    override fun onMessageReactionRemoveAll(event: MessageReactionRemoveAllEvent?) {
        super.onMessageReactionRemoveAll(event)
    }

    override fun onSelfUpdateName(event: SelfUpdateNameEvent?) {
        super.onSelfUpdateName(event)
    }

    override fun onCategoryUpdatePosition(event: CategoryUpdatePositionEvent?) {
        super.onCategoryUpdatePosition(event)
    }

    override fun onGroupMessageReactionAdd(event: GroupMessageReactionAddEvent?) {
        super.onGroupMessageReactionAdd(event)
    }

    override fun onPrivateMessageReactionRemove(event: PrivateMessageReactionRemoveEvent?) {
        super.onPrivateMessageReactionRemove(event)
    }

    override fun onGenericGuildVoice(event: GenericGuildVoiceEvent?) {
        super.onGenericGuildVoice(event)
    }

    override fun onGuildVoiceJoin(event: GuildVoiceJoinEvent?) {
        super.onGuildVoiceJoin(event)
    }

    override fun onTextChannelUpdatePermissions(event: TextChannelUpdatePermissionsEvent?) {
        super.onTextChannelUpdatePermissions(event)
    }

    override fun onGuildAvailable(event: GuildAvailableEvent?) {
        super.onGuildAvailable(event)
    }

    override fun onGroupMessageUpdate(event: GroupMessageUpdateEvent?) {
        super.onGroupMessageUpdate(event)
    }

    override fun onMessageReactionAdd(event: MessageReactionAddEvent?) {
        super.onMessageReactionAdd(event)
    }

    override fun onGroupUserJoin(event: GroupUserJoinEvent?) {
        super.onGroupUserJoin(event)
    }

    override fun onGuildVoiceUpdate(event: GuildVoiceUpdateEvent?) {
        super.onGuildVoiceUpdate(event)
    }

    override fun onGuildMemberRoleRemove(event: GuildMemberRoleRemoveEvent?) {
        super.onGuildMemberRoleRemove(event)
    }

    override fun onGroupMessageEmbed(event: GroupMessageEmbedEvent?) {
        super.onGroupMessageEmbed(event)
    }

    override fun onGenericRelationshipAdd(event: GenericRelationshipAddEvent?) {
        super.onGenericRelationshipAdd(event)
    }

    override fun onMessageBulkDelete(event: MessageBulkDeleteEvent?) {
        super.onMessageBulkDelete(event)
    }

    override fun onGuildVoiceMove(event: GuildVoiceMoveEvent?) {
        super.onGuildVoiceMove(event)
    }

    override fun onUnavailableGuildJoined(event: UnavailableGuildJoinedEvent?) {
        super.onUnavailableGuildJoined(event)
    }

    override fun onPrivateMessageReceived(event: PrivateMessageReceivedEvent?) {
        super.onPrivateMessageReceived(event)
    }

    override fun onGuildMessageReactionRemove(event: GuildMessageReactionRemoveEvent?) {
        super.onGuildMessageReactionRemove(event)
    }

    override fun onPrivateMessageDelete(event: PrivateMessageDeleteEvent?) {
        super.onPrivateMessageDelete(event)
    }

    override fun onPrivateMessageReactionRemoveAll(event: PrivateMessageReactionRemoveAllEvent?) {
        super.onPrivateMessageReactionRemoveAll(event)
    }

    override fun onGuildBan(event: GuildBanEvent?) {
        super.onGuildBan(event)
    }

    override fun onGenericRoleUpdate(event: GenericRoleUpdateEvent?) {
        super.onGenericRoleUpdate(event)
    }

    override fun onGuildVoiceLeave(event: GuildVoiceLeaveEvent?) {
        super.onGuildVoiceLeave(event)
    }

    override fun onGenericRelationship(event: GenericRelationshipEvent?) {
        super.onGenericRelationship(event)
    }

    override fun onGuildUpdateMFALevel(event: GuildUpdateMFALevelEvent?) {
        super.onGuildUpdateMFALevel(event)
    }

    override fun onCallVoiceSelfDeafen(event: CallVoiceSelfDeafenEvent?) {
        super.onCallVoiceSelfDeafen(event)
    }

    override fun onVoiceChannelUpdateName(event: VoiceChannelUpdateNameEvent?) {
        super.onVoiceChannelUpdateName(event)
    }

    override fun onCallVoiceJoin(event: CallVoiceJoinEvent?) {
        super.onCallVoiceJoin(event)
    }

    override fun onCallVoiceLeave(event: CallVoiceLeaveEvent?) {
        super.onCallVoiceLeave(event)
    }

    override fun onHttpRequest(event: HttpRequestEvent?) {
        super.onHttpRequest(event)
    }

    override fun onGenericGroupMessage(event: GenericGroupMessageEvent?) {
        super.onGenericGroupMessage(event)
    }

    override fun onPrivateMessageReactionAdd(event: PrivateMessageReactionAddEvent?) {
        super.onPrivateMessageReactionAdd(event)
    }

    override fun onTextChannelCreate(event: TextChannelCreateEvent?) {
        super.onTextChannelCreate(event)
    }

    override fun onUserAvatarUpdate(event: UserAvatarUpdateEvent?) {
        super.onUserAvatarUpdate(event)
    }

    override fun onGenericRole(event: GenericRoleEvent?) {
        super.onGenericRole(event)
    }

    override fun onGuildMemberLeave(event: GuildMemberLeaveEvent?) {
        super.onGuildMemberLeave(event)
    }

    override fun onMessageReactionRemove(event: MessageReactionRemoveEvent?) {
        super.onMessageReactionRemove(event)
    }

    override fun onGenericCallVoice(event: GenericCallVoiceEvent?) {
        super.onGenericCallVoice(event)
    }

    override fun onVoiceChannelUpdateParent(event: VoiceChannelUpdateParentEvent?) {
        super.onVoiceChannelUpdateParent(event)
    }

    override fun onGuildMemberNickChange(event: GuildMemberNickChangeEvent?) {
        super.onGuildMemberNickChange(event)
    }

    override fun onGenericEvent(event: Event?) {
        super.onGenericEvent(event)
    }

    override fun onVoiceChannelUpdateUserLimit(event: VoiceChannelUpdateUserLimitEvent?) {
        super.onVoiceChannelUpdateUserLimit(event)
    }

    override fun onGuildMessageUpdate(event: GuildMessageUpdateEvent?) {
        super.onGuildMessageUpdate(event)
    }

    override fun onGuildUpdateSplash(event: GuildUpdateSplashEvent?) {
        super.onGuildUpdateSplash(event)
    }

    override fun onCallCreate(event: CallCreateEvent?) {
        super.onCallCreate(event)
    }

    override fun onVoiceChannelUpdatePosition(event: VoiceChannelUpdatePositionEvent?) {
        super.onVoiceChannelUpdatePosition(event)
    }

    override fun onTextChannelUpdateTopic(event: TextChannelUpdateTopicEvent?) {
        super.onTextChannelUpdateTopic(event)
    }

    override fun onGuildUpdateAfkTimeout(event: GuildUpdateAfkTimeoutEvent?) {
        super.onGuildUpdateAfkTimeout(event)
    }

    override fun onGenericGroup(event: GenericGroupEvent?) {
        super.onGenericGroup(event)
    }

    override fun onSelfUpdateVerified(event: SelfUpdateVerifiedEvent?) {
        super.onSelfUpdateVerified(event)
    }

    override fun onMessageUpdate(event: MessageUpdateEvent?) {
        super.onMessageUpdate(event)
    }

    override fun onTextChannelDelete(event: TextChannelDeleteEvent?) {
        super.onTextChannelDelete(event)
    }

    override fun onGenericGuildMessageReaction(event: GenericGuildMessageReactionEvent?) {
        super.onGenericGuildMessageReaction(event)
    }

    override fun onGuildUpdateFeatures(event: GuildUpdateFeaturesEvent?) {
        super.onGuildUpdateFeatures(event)
    }

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent?) {
        super.onGuildMessageReceived(event)
    }

    override fun onGuildMemberRoleAdd(event: GuildMemberRoleAddEvent?) {
        super.onGuildMemberRoleAdd(event)
    }

    override fun onSelfUpdateMFA(event: SelfUpdateMFAEvent?) {
        super.onSelfUpdateMFA(event)
    }

    override fun onPrivateChannelDelete(event: PrivateChannelDeleteEvent?) {
        super.onPrivateChannelDelete(event)
    }

    override fun onGuildUpdateIcon(event: GuildUpdateIconEvent?) {
        super.onGuildUpdateIcon(event)
    }

    override fun onFriendAdded(event: FriendAddedEvent?) {
        super.onFriendAdded(event)
    }

    override fun onGenericCategory(event: GenericCategoryEvent?) {
        super.onGenericCategory(event)
    }

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent?) {
        super.onGuildMemberJoin(event)
    }

    override fun onTextChannelUpdateParent(event: TextChannelUpdateParentEvent?) {
        super.onTextChannelUpdateParent(event)
    }

    override fun onGuildVoiceGuildDeafen(event: GuildVoiceGuildDeafenEvent?) {
        super.onGuildVoiceGuildDeafen(event)
    }

    override fun onGuildVoiceSelfDeafen(event: GuildVoiceSelfDeafenEvent?) {
        super.onGuildVoiceSelfDeafen(event)
    }

    override fun onUserNameUpdate(event: UserNameUpdateEvent?) {
        super.onUserNameUpdate(event)
    }

    override fun onGuildVoiceSuppress(event: GuildVoiceSuppressEvent?) {
        super.onGuildVoiceSuppress(event)
    }

    override fun onGroupUpdateName(event: GroupUpdateNameEvent?) {
        super.onGroupUpdateName(event)
    }
}