package com.diegoferreiracaetano.pokedex.util

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> T.toJson(): String where T : Any =
    Json.encodeToString(this)

inline fun <reified T> String.fromJson(): T =
    Json.decodeFromString(this)