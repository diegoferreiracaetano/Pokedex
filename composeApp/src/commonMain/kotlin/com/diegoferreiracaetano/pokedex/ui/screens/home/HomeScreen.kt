package com.diegoferreiracaetano.pokedex.ui.screens.home

import AppSelection
import AppSelectionOption
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.diegoferreiracaetano.pokedex.data.util.OrderType
import com.diegoferreiracaetano.pokedex.data.util.OrderType.ASC
import com.diegoferreiracaetano.pokedex.data.util.OrderType.DESC
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
fun listOfTypes() = listOf(
    AppSelectionOption(
        stringResource(Res.string.all_types),
        "",
        MaterialTheme.colorScheme.onBackground
    )
) + pokemonList.map {
    AppSelectionOption(it.label(), it.name,it.color)
}

@Composable
fun listOrder() = listOf(
    AppSelectionOption(
        stringResource(Res.string.all_order_crescent_name),
        ASC,
        MaterialTheme.colorScheme.onBackground
    ),
    AppSelectionOption(
        stringResource(Res.string.all_order_decrescent_name),
        DESC,
        MaterialTheme.colorScheme.onBackground
    ),
)

@Composable
fun HomeScreen(
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinInject()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PokemonListScreen(
        list = uiState.success,
        onTabSelected,
        onChangeType = { search, type, order -> viewModel.list(search, type, order) },
        modifier,
    )
}

@Composable
fun PokemonListScreen(
    list: List<Pokemon>?,
    onTabSelected: (String) -> Unit,
    onChangeType: (String, String, OrderType) -> Unit,
    modifier: Modifier = Modifier,
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
            FilterRow(onChangeType = onChangeType)
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
fun FilterRow(
    onChangeType: (String, String, OrderType) -> Unit,
    modifier: Modifier = Modifier
) {
    var search by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("") }
    var selectedOrder by remember { mutableStateOf(ASC) }

    Column(
        modifier = modifier
    ) {
        AppSearchBar(onValueChange = {
            search = it
            onChangeType(search, selectedType, selectedOrder)
        })
        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            AppSelection(
                modifier = Modifier.weight(1f),
                list = listOfTypes(),
                selected = { _, selected ->
                    selectedType = selected.value as String
                    onChangeType(search, selectedType, selectedOrder)
                }
            )

            AppSelection(
                modifier = Modifier.weight(1f),
                list = listOrder(),
                selected = { _, selected ->
                    selectedOrder = selected.value as OrderType
                    onChangeType(search, selectedType, selectedOrder)
                }
            )
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    val list = listOf(
        Pokemon("001", "Pikachu", listOf(PokemonType.ELECTRIC), "", false)
    )
    PokemonListScreen(list, onTabSelected = {}, onChangeType = {_, _, _ ->})
}