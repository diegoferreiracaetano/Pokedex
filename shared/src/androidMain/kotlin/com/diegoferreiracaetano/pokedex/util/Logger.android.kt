package com.diegoferreiracaetano.pokedex.util

class AndroidLogger : Logger {
    override fun d(tag: String, message: String) {
        android.util.Log.d(tag, message)
    }
}

actual fun getLogger(): Logger = AndroidLogger()