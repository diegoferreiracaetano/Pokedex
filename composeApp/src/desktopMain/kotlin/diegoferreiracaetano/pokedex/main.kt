package diegoferreiracaetano.pokedex

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.diegoferreiracaetano.pokedex.App
import com.diegoferreiracaetano.pokedex.di.initKoin

fun main() = application {

    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Pokedex"
    ) {
        App()
    }
}