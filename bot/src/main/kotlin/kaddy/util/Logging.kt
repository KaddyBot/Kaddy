package kaddy.util

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import com.google.common.cache.CacheBuilder
import mu.KLogger
import mu.KotlinLogging
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

val Loggable.logger
    get() = Logging.getLogger(this)

object Logging {

    init {
        setRootLogLevel(Level.INFO)
    }

    private val logNames = mutableMapOf<Class<out LogOwner>, String>()
    private val logCache = CacheBuilder.newBuilder()
            .expireAfterAccess(5, TimeUnit.MINUTES)
            .maximumSize(300)
            .build<Class<out Loggable>, KLogger>()

    fun init(logOwner: Class<out LogOwner>, name: CharSequence = logOwner.name) {
        logNames[logOwner] = name.toString()
    }

    fun getLogger(loggable: Loggable): KLogger {
        if (loggable.logOwner !in logNames) {
            init(loggable.logOwner)
        }
        return logCache.get(loggable::class.java, {
            KotlinLogging.logger("${logNames[loggable.logOwner]}/${loggable::class.java.name}")
        })
    }

    fun setRootLogLevel(level: Level) {
        val logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        logger.level = level
    }
}