package com.diegoferreiracaetano.pokedex.ui.navigation

import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType
import com.diegoferreiracaetano.pokedex.ui.screens.login.AuthScreenType

sealed class ScreenRouter(val route: String) {
    object Home : ScreenRouter("home")
    object Onboarding : ScreenRouter("onboarding")
    object OnboardingFinish : ScreenRouter("onboarding_finish")

    object PreLogin: ScreenRouter("pre_login/{type}") {
        const val TYPE_ARG = "type"

        fun routeWithType(type: AuthScreenType): String {
            return "pre_login/${type.name}"
        }
    }

    object CreateAccount : ScreenRouter("create_account/{step}") {
        const val STEP_ARG = "step"

        fun routeWithStep(step: CreateAccountStepType): String {
            return "create_account/${step.name}"
        }
    }

    object Login : ScreenRouter("login")

    object ForgotPassword : ScreenRouter("forgot_password")

    object SendCode : ScreenRouter("send_code/{contact}") {
        const val CONTACT_ARG = "contact"

        fun routeWithContact(contact: String): String {
            return "send_code/${contact}"
        }
    }
}
