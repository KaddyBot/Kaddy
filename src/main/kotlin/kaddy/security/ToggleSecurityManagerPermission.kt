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