package com.diegoferreiracaetano.pokedex.domain.pokedex

import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun list(): Flow<List<Pokemon>>
    fun detail(id: Int): Flow<Pokemon>
}