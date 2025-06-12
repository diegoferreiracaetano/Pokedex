package com.diegoferreiracaetano.pokedex.ui.screens.forgotPassword

import com.diegoferreiracaetano.pokedex.ui.screens.forgotPassword.ChangePasswordType.FORGOT
import com.diegoferreiracaetano.pokedex.ui.screens.forgotPassword.ChangePasswordType.UPDATE
import org.jetbrains.compose.resources.StringResource
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.forgot_password_subtitle
import pokedex.composeapp.generated.resources.forgot_password_title
import pokedex.composeapp.generated.resources.update_password_subtitle
import pokedex.composeapp.generated.resources.update_password_title

enum class ChangePasswordType {
    FORGOT,
    UPDATE
}

fun ChangePasswordType.toUI() = when (this) {
    FORGOT -> ChangePasswordData(
        title = Res.string.forgot_password_title,
        subtitle = Res.string.forgot_password_subtitle
    )
    UPDATE -> ChangePasswordData(
        title = Res.string.update_password_title,
        subtitle = Res.string.update_password_subtitle,
    )
}

data class ChangePasswordData(
    val title: StringResource,
    val subtitle: StringResource
)