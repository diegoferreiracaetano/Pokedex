package com.diegoferreiracaetano.pokedex.util

interface Logger {
    fun d(tag: String, message: String)
}

expect fun getLogger(): Logger