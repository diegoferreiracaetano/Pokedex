package com.diegoferreiracaetano.pokedex.ui.navigation

sealed class ScreenRouter(val route: String) {
    object Home : ScreenRouter("home")
    object Onboarding : ScreenRouter("onboarding")
    object OnboardingFinish : ScreenRouter("onboarding_finish")
}