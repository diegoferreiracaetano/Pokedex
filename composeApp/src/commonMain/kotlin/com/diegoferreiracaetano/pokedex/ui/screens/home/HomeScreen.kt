package com.diegoferreiracaetano.pokedex.ui.screens.home

import AppSelection
import AppSelectionOption
import AppSelectionSimple
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.diegoferreiracaetano.pokedex.domain.pokedex.Pokemon
import com.diegoferreiracaetano.pokedex.domain.pokedex.PokemonType
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppBottomNavigation
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppContainer
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.PokemonCard
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.color
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.label
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.pokemonList
import com.diegoferreiracaetano.pokedex.ui.components.search.AppSearchBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.all_order_crescent_name
import pokedex.composeapp.generated.resources.all_order_decrescent_name
import pokedex.composeapp.generated.resources.all_types

@Composable
fun HomeScreen(
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinInject()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.list("")

    PokemonListScreen(
        list = uiState.success,
        onTabSelected,
        modifier
    )
}

@Composable
fun PokemonListScreen(
    list: List<Pokemon>?,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    AppContainer(
        modifier = modifier,
        bottomBar = AppBottomNavigation(
            onTabSelected = onTabSelected
        )
    ) { modifier ->
        Column(
            modifier = modifier
        ) {
            AppSearchBar()
            Spacer(modifier = Modifier.height(12.dp))
            FilterRow()
            Spacer(modifier = Modifier.height(12.dp))

            if (list.isNullOrEmpty()) {
                Text("vazio")
            } else {
                LazyColumn {
                    items(list) { pokemon ->
                        PokemonCard(pokemon = pokemon)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun FilterRow() {

    val list = listOf(
        AppSelectionOption(
            stringResource(Res.string.all_types),
            MaterialTheme.colorScheme.onBackground
        )
    ) + pokemonList.map {
        AppSelectionOption(it.label(),it.color)
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        AppSelection(
            modifier = Modifier.weight(1f),
            list = list,
            selected = { index, selected ->
                println("Opção selecionada: $selected")
            }
        )

        AppSelectionSimple(
            modifier = Modifier.weight(1f),
            list =  listOf(
                stringResource(Res.string.all_order_crescent_name),
                stringResource(Res.string.all_order_decrescent_name)
            ),
            selected = { index,  selected ->
                println("Opção selecionada: $selected")
            }
        )
    }
}


@Preview
@Composable
fun HomeScreenPreview() {

    val list = listOf(
        Pokemon("001", "Pikachu", listOf(PokemonType.ELECTRIC), "", false)
    )
    PokemonListScreen(list, onTabSelected = {})
}