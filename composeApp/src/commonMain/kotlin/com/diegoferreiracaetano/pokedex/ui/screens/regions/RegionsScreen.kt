package com.diegoferreiracaetano.pokedex.ui.screens.regions

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppBottomNavigation
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppContainer
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegionsScreen(
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    AppContainer(
        modifier = modifier,
        bottomBar = AppBottomNavigation(
            selectedRoute = ScreenRouter.Regions.route,
            onTabSelected = onTabSelected
        )
    ) { modifier ->
        Text(
            modifier = modifier,
            text = "REGIÕES",
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@Preview
@Composable
fun RegionsScreenPreview() {
    RegionsScreen(onTabSelected = {})
}