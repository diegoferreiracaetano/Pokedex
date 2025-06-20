package com.diegoferreiracaetano.pokedex.ui.navigation

import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType
import com.diegoferreiracaetano.pokedex.ui.screens.email.ValidateEmailType
import com.diegoferreiracaetano.pokedex.ui.screens.login.AuthScreenType

sealed class ScreenRouter(val route: String) {
    object Home : ScreenRouter("home")
    object Onboarding : ScreenRouter("onboarding")
    object OnboardingFinish : ScreenRouter("onboarding_finish")
    object Profile : ScreenRouter("profile")
    object EditName : ScreenRouter("edit_name")
    object EditPassword : ScreenRouter("edit_password")
    object Regions : ScreenRouter("regions")
    object Favorites : ScreenRouter("favorites")

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

    object ValidateEmail : ScreenRouter("validate_email/{validate}") {
        fun routeWithType(type: ValidateEmailType): String {
            return "validate_email/${type.name}"
        }
    }

    object SendCode : ScreenRouter("send_code/{contact}") {
        const val CONTACT_ARG = "contact"

        fun routeWithContact(contact: String): String {
            return "send_code/${contact}"
        }
    }
}
