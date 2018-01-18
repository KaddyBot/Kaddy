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