/*
 * This file is part of Kaddy.
 *
 * Copyright (C) 2018  Kaddy Team
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package kaddy.util

import dtmlibs.logging.Loggable
import dtmlibs.logging.Logging
import kaddy.Kaddy

internal object KaddyLoggable : Loggable {
    init {
        Logging.registerLogOwnerName(Kaddy::class.java, "Kaddy")
    }
    override val logOwner = Kaddy::class.java
}