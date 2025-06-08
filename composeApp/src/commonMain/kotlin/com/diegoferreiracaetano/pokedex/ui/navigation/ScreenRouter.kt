package com.diegoferreiracaetano.pokedex.ui.navigation

import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType

sealed class ScreenRouter(val route: String) {
    object Home : ScreenRouter("home")
    object Onboarding : ScreenRouter("onboarding")
    object OnboardingFinish : ScreenRouter("onboarding_finish")
    object CreateAccount : ScreenRouter("create_account/{step}") {
        const val STEP_ARG = "step"

        fun routeWithStep(step: CreateAccountStepType): String {
            return "create_account/${step.name}"
        }
    }
}
