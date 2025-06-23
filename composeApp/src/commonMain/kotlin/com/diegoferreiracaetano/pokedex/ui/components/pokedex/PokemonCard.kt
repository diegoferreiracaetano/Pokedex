package com.diegoferreiracaetano.pokedex.ui.components.pokedex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.domain.pokedex.Pokemon
import com.diegoferreiracaetano.pokedex.domain.pokedex.PokemonType
import com.diegoferreiracaetano.pokedex.ui.components.image.AppImage
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PokemonCard(pokemon: Pokemon) {
    Box {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .background(
                    color = pokemon.types.first().color.copy(alpha = 0.1f),
                    shape = MaterialTheme.shapes.large
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Nº${pokemon.number}",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    pokemon.types.forEach { type ->
                        TypeChip(type)
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.medium)
                    .background(typeColor(pokemon.types.first())),
                contentAlignment = Alignment.Center
            ) {
                AppImage(
                    imageURL = pokemon.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    contentAlignment = Alignment.TopEnd,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    FavoriteButton(
                        isFavorite = pokemon.isFavorite,
                        modifier = Modifier
                            .padding(6.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TypeChip(type: PokemonType) {

    Row(
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(type.color)
            .padding(horizontal = 12.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(20.dp) // Ajuste o tamanho conforme necessário
                .clip(CircleShape) // Corta o Box para um círculo
                .background(MaterialTheme.colorScheme.background), // Cor de fundo do círculo (opcional)
            contentAlignment = Alignment.Center // Centraliza a imagem dentro do círculo
        ) {

            Icon(
                type.icon,
                contentDescription = null,
               // contentScale = ContentScale.Crop,
                tint = type.color,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = type.label(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.background
        )
    }
}

fun typeColor(type: PokemonType): Color = type.color



@Composable
fun FavoriteButton(
    isFavorite: Boolean = false,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = { }) {
        Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null,
            tint = Color.White,
            modifier = modifier
                .background(Color.Black.copy(alpha = 0.2f), shape = CircleShape)
                .padding(4.dp)
        )
    }
}

@Preview
@Composable
fun TypeChipPreview() {
    TypeChip(PokemonType.GRASS)
}


@Preview
@Composable
fun PokemonCardPreview() {
    val pokemon = Pokemon(
        number = "001",
        name = "Bulbasaur",
        types = listOf(PokemonType.GRASS, PokemonType.POISON),
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
        isFavorite = false
    )

    PokedexTheme {
        PokemonCard(pokemon)
    }
}

