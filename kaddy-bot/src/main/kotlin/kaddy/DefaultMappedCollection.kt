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

internal class DefaultMappedCollection<out ElementType>(getCollection: () -> Collection<ElementType>,
                                                        private val getByKey: (Long) -> ElementType?)
    : IDMappedCollection<ElementType>, Collection<ElementType> by getCollection() {

    override fun get(key: Long): ElementType? = getByKey(key)
}