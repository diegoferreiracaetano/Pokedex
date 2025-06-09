package com.diegoferreiracaetano.pokedex.util
import platform.Foundation.NSLog

class IosLogger : Logger {
    override fun d(tag: String, message: String) {
        NSLog("KMP_LOG - %@: %@", tag, message)
    }
}

actual fun getLogger(): Logger = IosLogger()