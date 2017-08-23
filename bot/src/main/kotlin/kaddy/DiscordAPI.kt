package kaddy

import com.google.common.util.concurrent.FutureCallback
import de.btobastian.javacord.DiscordAPI
import java.util.concurrent.Future

class DiscordAPI internal constructor(internal val internalAPI: de.btobastian.javacord.DiscordAPI)
    : de.btobastian.javacord.DiscordAPI by internalAPI {

    override fun setWaitForServersOnStartup(wait: Boolean) {
        throw UnsupportedOperationException("This feature is not available to plugins.")
    }

    override fun connect(callback: FutureCallback<DiscordAPI>?) {
        throw UnsupportedOperationException("This feature is not available to plugins.")
    }

    override fun connectBlocking() {
        throw UnsupportedOperationException("This feature is not available to plugins.")
    }

    override fun setAutoReconnect(autoReconnect: Boolean) {
        throw UnsupportedOperationException("This feature is not available to plugins.")
    }

    override fun setToken(token: String?, bot: Boolean) {
        throw UnsupportedOperationException("This feature is not available to plugins.")
    }

    override fun disconnect() {
        throw UnsupportedOperationException("This feature is not available to plugins.")
    }

    override fun updateEmail(newEmail: String?): Future<Void> {
        throw UnsupportedOperationException("This feature is not available to plugins.")
    }

    override fun setPassword(password: String?) {
        throw UnsupportedOperationException("This feature is not available to plugins.")
    }

    override fun updatePassword(newPassword: String?): Future<Void> {
        throw UnsupportedOperationException("This feature is not available to plugins.")
    }

    override fun setEmail(email: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}