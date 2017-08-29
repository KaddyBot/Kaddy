package kaddy.util

import dtmlibs.logging.Loggable
import dtmlibs.logging.Logging
import kaddy.Kaddy

internal object KaddyLoggable : Loggable {
    init {
        Logging.registerLogOwner(Kaddy::class.java, "Kaddy")
    }
    override val logOwner = Kaddy::class.java
}