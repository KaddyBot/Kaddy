package kaddy.event.voicechannel

import de.btobastian.javacord.entities.VoiceChannel
import kaddy.event.Event

abstract class VoiceChannelEvent(val voiceChannel: VoiceChannel) : Event()