
package com.diegoferreiracaetano.pokedex.ui.screens.detail


import PokemonDetailsSection
import PokemonEvolutionsSection
import PokemonGenderSection
import PokemonInfoSection
import PokemonWeaknessesSection
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.pokemonList
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.test.AppTopBarPokemon
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.test.CollapsingScreenLayout
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.test.Evolution
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.test.PokemonDetail
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.test.PokemonExpandedHeaderContent
import com.diegoferreiracaetano.pokedex.ui.theme.Grass
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


 val bulbasaurMock = PokemonDetail(
    name = "Bulbasaur",
    number = "Nº001",
    backgroundColor = Grass,
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/1.gif",
    types = listOf("Grama", "Venenoso"),
    description = "Há uma semente de planta nas costas desde o dia em que este Pokémon nasce. A semente cresce lentamente.",
    weight = "6,9 kg",
    height = "0,7 m",
    category = "Seed",
    ability = "Overgrow",
    genderMaleRatio = 0.875f,
    weaknesses = listOf("Fogo", "Psíquico", "Voador", "Gelo"),
    evolutions = listOf(
        Evolution(
            "Bulbasaur",
            "Nº001",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/1.gif",
            listOf("Grama", "Venenoso"),
            null
        ),
        Evolution(
            "Ivysaur",
            "Nº002",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/2.gif",
            listOf("Grama", "Venenoso"),
            16
        ),
        Evolution(
            "Venusaur",
            "Nº003",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/3.gif",
            listOf("Grama", "Venenoso"),
            36
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    id: String,
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    AppTopBarPokemon(
        imageUrl = bulbasaurMock.imageUrl,
        backgroundColor = bulbasaurMock.backgroundColor,
        onBack = onBack,
        onFavorite = {  },
        modifier = modifier
    ) { modifier ->


    }
}

//    val headerBackgroundColor = Color(0xFF6BBA79)
//
//    CollapsingScreenLayout(
//        toolbarTitle = bulbasaurMock.name,
//        toolbarBackgroundColor = headerBackgroundColor,
//        navigationIcon = {
//            IconButton(onClick = { /* TODO: handle back */ }) {
//                Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.White)
//            }
//        },
//        actions = {
//            IconButton(onClick = { /* TODO: handle favorite */ }) {
//                Icon(Icons.Filled.FavoriteBorder, contentDescription = "Favoritar", tint = Color.White)
//            }
//        },
//        expandedHeaderContent = { collapsedFraction ->
//            PokemonExpandedHeaderContent(
//                pokemon = bulbasaurMock,
//                collapsedFraction = collapsedFraction,
//                backgroundColor = headerBackgroundColor
//            )
//        },
//        screenContent = { contentPadding ->
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                contentPadding = contentPadding
//            ) {
//                item {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(MaterialTheme.colorScheme.background)
//                            .padding(top = 16.dp)
//                    ) {
//                        PokemonInfoSection(bulbasaurMock)
//                        Spacer(modifier = Modifier.height(16.dp))
//                        PokemonDetailsSection(bulbasaurMock)
//                        Spacer(modifier = Modifier.height(16.dp))
//                        PokemonGenderSection(bulbasaurMock)
//                        Spacer(modifier = Modifier.height(16.dp))
//                        PokemonWeaknessesSection(bulbasaurMock)
//                        Spacer(modifier = Modifier.height(16.dp))
//                        PokemonEvolutionsSection(bulbasaurMock)
//                        Spacer(modifier = Modifier.height(32.dp))
//                    }
//                }
//            }
//        }
//    )



@Preview
@Composable
fun PreviewPokemonDetailScreen() {
    PokedexTheme {
        PokemonDetailScreen("1")
    }
}
