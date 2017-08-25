package kaddy.util

import kaddy.Kaddy

internal object KaddyLoggable : Loggable {
    init {
        Logging.init(Kaddy::class.java, "Kaddy")
    }
    override val logOwner = Kaddy::class.java
}