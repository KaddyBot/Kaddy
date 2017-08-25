package kaddy.plugin

class InvalidDescriptionException(message: CharSequence? = null, cause: Throwable? = null)
    : Exception(message.toString(), cause)