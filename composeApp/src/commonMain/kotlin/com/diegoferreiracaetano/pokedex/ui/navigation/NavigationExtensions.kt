package com.diegoferreiracaetano.pokedex.ui.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.savedstate.read

inline fun <reified T : Enum<T>> NavBackStackEntry.readEnumOrDefault(
    key: String,
    default: T
): T = runCatching {
    val value = this.arguments?.read { getString(key) }.orEmpty()
    enumValueOf<T>(value)
}.getOrDefault(default)

fun NavController.navigateToRoute(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateClearBackStackTo(route: String) {
    navigate(route) {
        popUpTo(0) { inclusive = true } // Limpa toda a stack
        launchSingleTop = true
    }
}