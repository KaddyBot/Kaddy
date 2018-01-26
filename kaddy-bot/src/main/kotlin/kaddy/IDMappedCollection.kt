/*
 * This file is part of Kaddy.
 *
 * Copyright (C) 2018  Kaddy Team
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package kaddy

import net.dv8tion.jda.core.utils.MiscUtil

interface IDMappedCollection<out ElementType> : MappedCollection<Long, ElementType> {

    /**
     * Returns a member of this collection that is mapped to the given id.
     *
     * @param id The id that represents a possible member of this collection.
     * @return The element mapped to the given id if the id exists, otherwise null.
     */
    operator fun get(id: CharSequence): ElementType? {
        return get(MiscUtil.parseSnowflake(id.toString()))
    }
}