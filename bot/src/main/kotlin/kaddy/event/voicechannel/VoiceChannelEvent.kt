package kaddy.event.voicechannel

import de.btobastian.javacord.entities.VoiceChannel
import kaddy.event.Event

abstract class VoiceChannelEvent(open val voiceChannel: VoiceChannel) : Event()