package com.diegoferreiracaetano.pokedex.data.pokedex.source.remote

import com.diegoferreiracaetano.pokedex.data.util.OrderType
import com.diegoferreiracaetano.pokedex.domain.pokedex.Pokemon
import com.diegoferreiracaetano.pokedex.domain.pokedex.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryRemote(
    private val dataSource: PokemonLocalDataSource
) : PokemonRepository {

    override fun list(search: String, type: String, order: OrderType): Flow<List<Pokemon>> = flow {

         val result = dataSource.fetchPokemonList().results.map {
            dataSource.fetchPokemonDetails(it.url.substringAfter("pokemon/").removeSuffix("/").toInt()).toDomain()
        }

        val filteredByType = if (type.isNotBlank()) {
            result.filter { pokemon ->
                pokemon.types.any { it.name.equals(type, ignoreCase = true) }
            }
        } else {
            result
        }

        val filteredBySearch = if (search.isNotBlank()) {
            filteredByType.filter { pokemon ->
                pokemon.name.contains(search, ignoreCase = true)
            }
        } else {
            filteredByType
        }

        val ordered = when (order) {
            OrderType.ASC -> filteredBySearch.sortedBy { it.name }
            OrderType.DESC -> filteredBySearch.sortedByDescending { it.name }
        }

        emit(ordered)
    }

    override fun detail(id: Int) = flow {
        val result = dataSource.fetchPokemonDetails(id).toDomain()

        emit(result)
    }
}