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
package kaddy.security

import java.security.Permission

internal class ToggleSecurityManagerPermission : Permission(NAME) {

    override fun hashCode(): Int = NAME.hashCode()

    override fun equals(other: Any?): Boolean = other is ToggleSecurityManagerPermission

    override fun getActions(): String = ""

    override fun implies(permission: Permission?): Boolean = this == permission

    companion object {
        private const val serialVersionUID = 4812713037565136922L
        private const val NAME = "ToggleSecurityManagerPermission"
    }
}