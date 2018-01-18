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

import java.io.FileDescriptor
import java.net.InetAddress
import java.security.Permission

internal class SelectiveSecurityManager(enabledByDefault: Boolean) : SecurityManager() {

    private val enabledFlag: ThreadLocal<Boolean> = object : ThreadLocal<Boolean>() {
        override fun initialValue(): Boolean = enabledByDefault

        override fun set(value: Boolean?) {
            val securityManager = System.getSecurityManager()
            securityManager?.checkPermission(TOGGLE_PERMISSION)
            super.set(value)
        }
    }

    var enabledForCurrentThread: Boolean
        get() = enabledFlag.get()
        set(value) = enabledFlag.set(value)

    companion object {
        private val TOGGLE_PERMISSION = ToggleSecurityManagerPermission()
    }

    override fun checkPermission(perm: Permission?) {
        if (perm is ToggleSecurityManagerPermission) super.checkPermission(perm)
    }

    override fun checkPermission(perm: Permission?, context: Any?) {
        if (perm is ToggleSecurityManagerPermission) super.checkPermission(perm, context)
    }

    override fun checkExit(status: Int) {
        if (enabledForCurrentThread) throw SecurityException("Not allowed to call System.exit");
    }

    override fun checkDelete(file: String?) {

    }

    override fun checkPropertiesAccess() {

    }

    override fun checkAccess(t: Thread?) {

    }

    override fun checkAccess(g: ThreadGroup?) {

    }

    override fun checkAwtEventQueueAccess() {

    }

    override fun checkExec(cmd: String?) {
        if (enabledForCurrentThread)  throw SecurityException("Not allowed to create subprocesses");
    }

    override fun checkListen(port: Int) {

    }

    override fun checkLink(lib: String?) {

    }

    override fun checkPropertyAccess(key: String?) {

    }

    override fun checkPackageDefinition(pkg: String?) {

    }

    override fun checkMulticast(maddr: InetAddress?) {

    }

    override fun checkMulticast(maddr: InetAddress?, ttl: Byte) {

    }

    override fun checkPackageAccess(pkg: String?) {

    }

    override fun checkRead(fd: FileDescriptor?) {

    }

    override fun checkRead(file: String?) {

    }

    override fun checkRead(file: String?, context: Any?) {

    }

    override fun checkAccept(host: String?, port: Int) {

    }

    override fun checkSystemClipboardAccess() {
        if (enabledForCurrentThread)  throw SecurityException("Not allowed to access system clipboard");
    }

    override fun checkWrite(fd: FileDescriptor?) {

    }

    override fun checkWrite(file: String?) {

    }

    override fun checkPrintJobAccess() {
        if (enabledForCurrentThread)  throw SecurityException("Not allowed to access print jobs");
    }

    override fun checkMemberAccess(clazz: Class<*>?, which: Int) {

    }

    override fun checkCreateClassLoader() {

    }

    override fun checkConnect(host: String?, port: Int) {

    }

    override fun checkConnect(host: String?, port: Int, context: Any?) {

    }

    override fun checkSetFactory() {

    }
}
