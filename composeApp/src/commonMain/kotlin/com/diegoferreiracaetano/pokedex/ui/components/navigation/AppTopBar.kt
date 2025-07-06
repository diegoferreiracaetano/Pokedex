package com.diegoferreiracaetano.pokedex.ui.components.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.diegoferreiracaetano.pokedex.ui.util.contrastTextColor
import org.jetbrains.compose.resources.stringResource
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_back
import pokedex.composeapp.generated.resources.action_favorite

data class AppTopBar(
    val title: String,
    val onBack: () -> Unit = {},
    val onFavorite: () -> Unit = {},
    val backgroundColor: Color = Color.Unspecified,
    val useTransparent: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
object AppTopBarFactory {

    @Composable
    operator fun invoke(
        config: AppTopBar,
        scrollBehavior: TopAppBarScrollBehavior? = null
    ) {
        if (config.useTransparent) {
            AppTopBarTransparent(
                backgroundColor = config.backgroundColor,
                onBack = config.onBack,
                onFavorite = config.onFavorite,
                scrollBehavior = scrollBehavior,
            )
        } else {
            AppTopBarDefault(
                title = config.title,
                onBack = config.onBack,
                scrollBehavior = scrollBehavior
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarDefault(
    title: String,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                title,
                maxLines = 1,
                style = MaterialTheme.typography.labelLarge
            )
        },

        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.action_back)
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarTransparent(
    backgroundColor: Color,
    onBack: () -> Unit,
    onFavorite: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.action_back),
                    tint = backgroundColor.contrastTextColor()
                )
            }
        },
        actions = {
            IconButton(onClick = onFavorite) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(Res.string.action_favorite),
                    tint = backgroundColor.contrastTextColor()
                )
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth(),
        scrollBehavior = scrollBehavior,
    )
}