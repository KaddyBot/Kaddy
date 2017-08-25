package kaddy.plugin

class InvalidPluginException(message: String? = null, cause: Throwable? = null)
    : Exception(message, cause)