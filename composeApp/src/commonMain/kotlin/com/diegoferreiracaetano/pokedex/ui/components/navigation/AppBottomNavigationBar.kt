package com.diegoferreiracaetano.pokedex.ui.components.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CatchingPokemon
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

data class AppBottomNavigation(
    val selectedRoute: String = tabList.first().route,
    val items: List<AppNavigationTab> = tabList,
    val onTabSelected: (String) -> Unit,
)

data class AppNavigationTab(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)


@Composable
fun AppBottomNavigationBar(
    items: List<AppNavigationTab>,
    selectedRoute: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavigationBar {

            val customColors: NavigationBarItemColors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.surfaceBright
            )

            items.forEach { tab ->
                val isSelected = tab.route == selectedRoute
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            onTabSelected(tab.route)
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                            contentDescription = tab.label,
                            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                        )
                    },
                    label = {
                        Text(
                            text = tab.label,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                        )
                    },
                    colors = customColors
                )
            }
        }
    }
}

enum class TabItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    HOME(ScreenRouter.Home.route, "Pokédex", Icons.Filled.CatchingPokemon, Icons.Outlined.CatchingPokemon),
    Regions(ScreenRouter.Regions.route, "Regiões", Icons.Filled.Place, Icons.Outlined.Place),
    Favorites(ScreenRouter.Favorites.route, "Favoritos", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder),
    Account(ScreenRouter.Profile.route, "Conta", Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle)
}

val tabList = TabItem.entries.map {
    AppNavigationTab(
        route = it.route,
        label = it.label,
        selectedIcon = it.selectedIcon,
        unselectedIcon = it.unselectedIcon
    )
}

@Preview()
@Composable
fun BottomNavigationBarPreview() {
    PokedexTheme {
        AppBottomNavigationBar(
            items = tabList,
            selectedRoute = TabItem.HOME.route,
            onTabSelected = { selectedTab ->
                println("Selecionado: ${selectedTab}")
            }
        )
    }
}
