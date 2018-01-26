/*
 * This file is part of Kaddy.
 *
 * Copyright (C) 2018  Kaddy Team
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package kaddy.event

import com.github.plugkit.event.Event
import com.github.plugkit.plugin.PluginManager
import kaddy.Kaddy

/**
 * Represents an event for Kaddy.
 *
 * All events require a static method named getHandlerList() which returns the same [HandlerList] as [.getHandlers].
 *
 * @see PluginManager.callEvent
 * @see PluginManager.registerEvents
 */
abstract class Event : Event<Kaddy>() {
    internal fun call(kaddy: Kaddy) {
        kaddy.pluginManager.callEvent(this)
    }
}