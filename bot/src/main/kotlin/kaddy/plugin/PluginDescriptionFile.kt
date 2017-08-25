package kaddy.plugin

import com.typesafe.config.Config

class PluginDescriptionFile(val name: String, val version: String, val main: String, val authors: String) {

    constructor(config: Config) : this(config.getString("name"), config.getString("version"),
            config.getString("main"), config.getString("authors"))

    val fullName
        get() = "$name v $version"

    init {
        if (!NAME_PATTERN.toRegex().matches(name)) {
            throw InvalidDescriptionException("Name $name contains invalid characters")
        }
    }

    companion object {
        const val NAME_PATTERN = """^[A-Za-z0-9_.-]+$"""
    }
}