package com.diegoferreiracaetano.pokedex

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.diegoferreiracaetano.pokedex.ui.navigation.AppNavGraph
import com.diegoferreiracaetano.pokedex.ui.screens.splash.SplashScreen
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    PokedexTheme {
        var showLandingScreen by remember { mutableStateOf(true) }
        if (showLandingScreen) {
            SplashScreen(onTimeout = { showLandingScreen = false })
        } else {
            AppNavGraph()
        }
    }
}