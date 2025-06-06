package com.diegoferreiracaetano.pokedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform