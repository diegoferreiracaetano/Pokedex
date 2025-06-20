package com.diegoferreiracaetano.pokedex.ui.screens.favorites

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppBottomNavigation
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppContainer
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FavoritesScreen(
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    AppContainer(
        modifier = modifier,
        bottomBar = AppBottomNavigation(
            selectedRoute = ScreenRouter.Favorites.route,
            onTabSelected = onTabSelected
        )
    ) { modifier ->
        Text(
            modifier = modifier,
            text = "FAVORITOS",
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen(onTabSelected = {})
}