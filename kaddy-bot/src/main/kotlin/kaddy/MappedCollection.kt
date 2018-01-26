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