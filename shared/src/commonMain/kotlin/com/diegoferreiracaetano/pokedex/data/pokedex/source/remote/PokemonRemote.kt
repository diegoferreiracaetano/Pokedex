package com.diegoferreiracaetano.pokedex.data.pokedex.source.remote

import com.diegoferreiracaetano.pokedex.domain.pokedex.Pokemon
import com.diegoferreiracaetano.pokedex.domain.pokedex.PokemonType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListRemote(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedAPIResourceDto>
)

@Serializable
data class PokemonRemote(
    val id: Int,
    val name: String,
    val sprites: SpritesRemote,
    val types: List<PokemonTypeSlotRemote>
)

@Serializable
data class SpritesRemote(
    @SerialName("front_default")
    val frontDefault: String?
)

@Serializable
data class PokemonTypeSlotRemote(
    val slot: Int,
    val type: TypeRemote
)

@Serializable
data class TypeRemote(
    val name: String,
    val url: String
)

fun PokemonRemote.toDomain(): Pokemon {
    return Pokemon(
        number = "#${id.toString().padStart(3, '0')}",
        name = name.replaceFirstChar { it.uppercase() },
        types = this.types.map { PokemonType.valueOf(it.type.name.uppercase()) },
        imageUrl = sprites.frontDefault ?: "",
        isFavorite = false
    )
}

@Serializable
data class NamedAPIResourceDto(
    val name: String,
    val url: String
)