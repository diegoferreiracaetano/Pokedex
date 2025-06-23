package com.diegoferreiracaetano.pokedex.domain.pokedex

data class Pokemon(
    val number: String,
    val name: String,
    val types: List<PokemonType>,
    val imageUrl: String,
    val isFavorite: Boolean
)