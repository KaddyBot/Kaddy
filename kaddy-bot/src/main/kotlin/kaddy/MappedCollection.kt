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

/**
 * A special collection that maps all of its elements to a key of some type.
 */
interface MappedCollection<in KeyType, out ElementType> : Collection<ElementType> {

    /**
     * Returns a member of this collection that is mapped to the given key (or id).
     *
     * @param key The key that represents a possible member of this collection.
     * @return The element mapped to the given key if the key exists, otherwise null.
     */
    operator fun get(key: KeyType): ElementType?
}