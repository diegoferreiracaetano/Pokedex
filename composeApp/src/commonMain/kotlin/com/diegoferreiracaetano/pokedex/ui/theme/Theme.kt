package com.diegoferreiracaetano.pokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

val LightColorScheme = lightColorScheme(
    primary = BluePrimary,
    onPrimary = BlueOnPrimary,
    primaryContainer = BluePrimaryContainer,
    onPrimaryContainer = BlueOnPrimaryContainer,

    secondary = RedSecondary,
    onSecondary = RedOnSecondary,
    secondaryContainer = RedSecondaryContainer,
    onSecondaryContainer = RedOnSecondaryContainer,

    background = LightBackground,
    onBackground = LightOnBackground,

    surface = SurfaceLight,
    onSurface = TextLight,
    onSurfaceVariant = DarkBackground,

    error = ErrorLight,
    onError = OnErrorLight
)

val DarkColorScheme = darkColorScheme(

    primary = BluePrimaryDark,
    onPrimary = BlueOnPrimaryDark,
    primaryContainer = BlueOnPrimaryContainerDark,
    onPrimaryContainer = BluePrimaryContainer,

    secondary = ErrorDark,
    onSecondary = OnErrorDark,
    secondaryContainer = RedSecondaryContainer,
    onSecondaryContainer = RedOnSecondaryContainer,

    background = DarkBackground,
    onBackground = DarkOnBackground,

    surface = SurfaceDark,
    onSurface = TextDark,
    onSurfaceVariant = DarkBackground,

    error = ErrorDark,
    onError = OnErrorDark
)


@Composable
fun PokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = PokedexTypography(),
        shapes = Shapes
    ) {
        Surface(
            color = colorScheme.surface
        ) {
            content()
        }
    }
}