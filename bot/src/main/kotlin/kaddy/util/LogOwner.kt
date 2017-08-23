package kaddy.util

interface LogOwner : Loggable {
    override val logOwner
        get() = this::class.java
}