package com.diegoferreiracaetano.pokedex.domain.pokedex

import com.diegoferreiracaetano.pokedex.data.util.OrderType
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun list(search: String, type: String, order: OrderType): Flow<List<Pokemon>>
    fun detail(id: Int): Flow<Pokemon>
}