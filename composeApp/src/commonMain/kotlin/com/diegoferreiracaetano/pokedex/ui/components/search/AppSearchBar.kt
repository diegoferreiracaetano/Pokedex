package com.diegoferreiracaetano.pokedex.ui.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.components.textfield.AppTextField
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.search_pokemon_placeholder

@Composable
fun AppSearchBar(
    placeholder: StringResource = Res.string.search_pokemon_placeholder,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }

    AppTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        placeholder = placeholder,
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    )
}

@Preview
@Composable
fun AppSearchBarPreview() {
    PokedexTheme {
        AppSearchBar(
            modifier = Modifier.padding(16.dp),
            placeholder = Res.string.search_pokemon_placeholder
        )
    }
}
