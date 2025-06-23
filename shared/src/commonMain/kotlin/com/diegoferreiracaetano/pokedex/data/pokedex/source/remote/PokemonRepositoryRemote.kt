package com.diegoferreiracaetano.pokedex.data.pokedex.source.remote

import com.diegoferreiracaetano.pokedex.domain.pokedex.Pokemon
import com.diegoferreiracaetano.pokedex.domain.pokedex.PokemonRepository
import com.diegoferreiracaetano.pokedex.util.getLogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryRemote(
    private val dataSource: PokemonLocalDataSource
) : PokemonRepository {

    override fun list(): Flow<List<Pokemon>> = flow {

         val result = dataSource.fetchPokemonList().results.map {
            dataSource.fetchPokemonDetails(it.url.substringAfter("pokemon/").removeSuffix("/").toInt()).toDomain()
        }

        emit(result)
    }

    override fun detail(id: Int) = flow {
        val result = dataSource.fetchPokemonDetails(id).toDomain()

        emit(result)
    }
}