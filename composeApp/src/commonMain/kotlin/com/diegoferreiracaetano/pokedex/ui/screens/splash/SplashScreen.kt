package com.diegoferreiracaetano.pokedex.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.pokemon_logo

private const val SplashWaitTime: Long = 2000

@Composable
fun SplashScreen(onTimeout: () -> Unit, modifier: Modifier = Modifier) {
    val offsetX = remember { Animatable(0f) }
    val currentOnTimeout by rememberUpdatedState(onTimeout)
    val density = LocalDensity.current

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        contentAlignment = Alignment.Center
    ) {
        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            val targetOffset = with(density) { 500.dp.toPx() }
            offsetX.animateTo(
                targetOffset,
                animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
            )

            currentOnTimeout()
        }

        Image(
            painter = painterResource(Res.drawable.pokemon_logo),
            contentDescription = null,
            modifier = Modifier
                .heightIn(max = 50.dp)
                .offset { IntOffset(offsetX.value.toInt(), 0) }

        )
    }
}



@Preview
@Composable
private fun SplashScreenPreview() {
    PokedexTheme {
        SplashScreen({ })
    }
}