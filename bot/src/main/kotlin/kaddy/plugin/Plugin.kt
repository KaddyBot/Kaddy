package kaddy.plugin

import kaddy.util.LogOwner
import mu.KLogger

interface Plugin : LogOwner {

    val logger: KLogger
}