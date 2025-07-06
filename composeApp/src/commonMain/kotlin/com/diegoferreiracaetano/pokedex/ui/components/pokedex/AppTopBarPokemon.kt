package com.diegoferreiracaetano.pokedex.ui.components.pokedex.test

import PokemonDetailsSection
import PokemonEvolutionsSection
import PokemonGenderSection
import PokemonInfoName
import PokemonInfoSection
import PokemonWeaknessesSection
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.diegoferreiracaetano.pokedex.ui.components.image.AppImage
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppContainer
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppTopBar
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.ResponsiveBackgroundCircle
import com.diegoferreiracaetano.pokedex.ui.screens.detail.bulbasaurMock
import com.diegoferreiracaetano.pokedex.ui.theme.Grass
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import com.diegoferreiracaetano.pokedex.ui.util.contrastTextColor
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_back
import pokedex.composeapp.generated.resources.action_favorite
import pokedex.composeapp.generated.resources.ic_leaf

@Composable
fun BackgroundCircle(
    backgroundColor: Color = Color.Green,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()
        val halfHeight = height / 9f

        val colorGradient = Brush.horizontalGradient(
            colors = listOf(
                backgroundColor,
                backgroundColor.copy(alpha = 0.6f)
            )
        )

        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(halfHeight.dp)) {
            val radius = 200.dp.toPx()

            val rectHeight = halfHeight - radius
            drawCircle(
                brush =  colorGradient,
                radius = width,
                center = Offset(width / 2, rectHeight)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarPokemon(
    imageUrl: String,
    backgroundColor: Color = Grass,
    contentDescription: String? = null,
    onBack: () -> Unit = {},
    onFavorite: () -> Unit = {},
    modifier: Modifier = Modifier,
    state: ScrollState = rememberScrollState(),
    content: @Composable (Modifier) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )

    AppContainer(
        modifier = modifier.fillMaxSize(),
        scrollBehavior = scrollBehavior,
        topBar = AppTopBar(
            title = "",
            backgroundColor = backgroundColor,
            useTransparent = true,
            onBack = onBack,
            onFavorite = onFavorite
        )
    ) { modifierInner ->

        Box(modifier.verticalScroll(state).fixedContent()) {
            ResponsiveBackgroundCircle(
                backgroundColor = backgroundColor,
                modifier = modifier
                    .zIndex(0f)
            )

            Column(modifierInner) {

                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .sizeIn(minHeight = 240.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    val imageSize = (maxWidth * 0.5f).coerceAtMost(240.dp)

                    AppImage(
                        imageURL = imageUrl,
                        contentDescription = contentDescription,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .size(imageSize)
                            .padding(top = 24.dp)
                    )
                }

                PokemonInfoName(bulbasaurMock)
                Spacer(modifier = Modifier.height(16.dp))
                PokemonInfoSection(bulbasaurMock)
                Spacer(modifier = Modifier.height(16.dp))
                PokemonDetailsSection(bulbasaurMock)
                Spacer(modifier = Modifier.height(16.dp))
                PokemonGenderSection(bulbasaurMock)
                Spacer(modifier = Modifier.height(16.dp))
                PokemonWeaknessesSection(bulbasaurMock)
                Spacer(modifier = Modifier.height(16.dp))
                PokemonEvolutionsSection(bulbasaurMock)
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview
@Composable
fun AppTopBarPokemonPreview() {
    PokedexTheme {
        AppTopBarPokemon(
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/1.gif",
        ) { modifier ->
            Column(modifier) {
                Text(text = "Bulbasaur", style = MaterialTheme.typography.titleMedium)
                Text(text = "N°001", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}