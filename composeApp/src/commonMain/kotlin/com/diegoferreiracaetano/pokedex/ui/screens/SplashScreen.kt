package com.diegoferreiracaetano.pokedex.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.pokemon_logo

private const val SplashWaitTime: Long = 2000

@Composable
fun SplashScreen(onTimeout: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier =
            modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.onSurfaceVariant)

        , contentAlignment = Alignment.Center) {
        val currentOnTimeout by rememberUpdatedState(onTimeout)
        LaunchedEffect(onTimeout) {
            delay(SplashWaitTime)
            currentOnTimeout()
        }
        Image(painterResource(Res.drawable.pokemon_logo), contentDescription = null)
    }
}



@Preview
@Composable
private fun SplashScreenPreview() {
    PokedexTheme {
        SplashScreen({ })
    }
}