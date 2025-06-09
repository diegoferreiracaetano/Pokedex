package com.diegoferreiracaetano.pokedex.ui.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ScreenLoading(
    modifier: Modifier = Modifier,
    showAnimation: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        if(showAnimation)
            PokeballLoading()
        else
            AppLoading()
    }
}

@Preview
@Composable
fun ScreenLoadingPreview() {
    PokedexTheme {
        ScreenLoading()
    }
}