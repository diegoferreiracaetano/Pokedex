package com.diegoferreiracaetano.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.diegoferreiracaetano.pokedex.ui.screens.splash.SplashScreen
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_Pokedex)

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun AppButtonPreview() {
    PokedexTheme {
        SplashScreen({})
    }
}