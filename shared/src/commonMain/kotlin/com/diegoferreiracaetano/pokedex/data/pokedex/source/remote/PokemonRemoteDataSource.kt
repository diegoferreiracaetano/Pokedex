package com.diegoferreiracaetano.pokedex.data.pokedex.source.remote

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json

class PokemonLocalDataSource {

    private val accessMutex = Mutex()

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }

    suspend fun fetchPokemonDetails(id: Int) = accessMutex.withLock {
      //  delay(SERVICE_LATENCY_IN_MILLIS)

        when (id) {
            1 -> json.decodeFromString<PokemonRemote>(bulbasaurJson)
            2 -> json.decodeFromString<PokemonRemote>(ivysaurJson)
            4 -> json.decodeFromString<PokemonRemote>(charmanderJson)
            7 -> json.decodeFromString<PokemonRemote>(squirtleJson)
            25 -> json.decodeFromString<PokemonRemote>(pikachuJson)
            else -> throw IllegalArgumentException("Pokemon with ID $id not found in local data.")
        }
    }

    suspend fun fetchPokemonList() = accessMutex.withLock {
        //delay(SERVICE_LATENCY_IN_MILLIS)
        json.decodeFromString<PokemonListRemote>(pokemonListJson)
    }
}

private const val SERVICE_LATENCY_IN_MILLIS = 2000L