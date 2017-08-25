package kaddy.plugin

class InvalidPluginException(message: CharSequence? = null, cause: Throwable? = null)
    : Exception(message.toString(), cause)