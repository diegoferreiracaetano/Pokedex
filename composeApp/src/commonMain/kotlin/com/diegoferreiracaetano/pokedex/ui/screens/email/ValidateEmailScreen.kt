package com.diegoferreiracaetano.pokedex.ui.screens.email

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.diegoferreiracaetano.pokedex.domain.user.EmailValidator
import com.diegoferreiracaetano.pokedex.ui.components.templantes.FormData
import com.diegoferreiracaetano.pokedex.ui.components.templantes.FormScreen
import com.diegoferreiracaetano.pokedex.ui.components.textfield.TextFieldType
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_continue
import pokedex.composeapp.generated.resources.create_email_title
import pokedex.composeapp.generated.resources.forgot_password_description
import pokedex.composeapp.generated.resources.forgot_password_subtitle
import pokedex.composeapp.generated.resources.forgot_password_title
import pokedex.composeapp.generated.resources.title_email
import pokedex.composeapp.generated.resources.update_password_description
import pokedex.composeapp.generated.resources.update_password_subtitle
import pokedex.composeapp.generated.resources.update_password_title

enum class ValidateEmailType {
    FORGOT,
    UPDATE
}

fun ValidateEmailType.toUI() = when (this) {
    ValidateEmailType.FORGOT -> FormData(
        Res.string.forgot_password_title,
        Res.string.forgot_password_title,
        Res.string.forgot_password_subtitle,
        Res.string.title_email,
        Res.string.forgot_password_description,
        Res.string.action_continue,
        TextFieldType.EMAIL,
        EmailValidator
    )
    ValidateEmailType.UPDATE -> FormData(
        Res.string.update_password_title,
        Res.string.create_email_title,
        Res.string.update_password_subtitle,
        Res.string.title_email,
        Res.string.update_password_description,
        Res.string.action_continue,
        TextFieldType.EMAIL,
        EmailValidator
    )
}

@Composable
fun ValidateEmailScreen(
    type: ValidateEmailType,
    onSendCode: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ValidateEmailViewModel = koinInject()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.success) {
        uiState.success?.let {
            onSendCode(it)
        }
    }

    ValidateEmailContent(
        type = type,
        isLoading = uiState.isLoading,
        error = uiState.error,
        onRecoveryPassword = { email->
            viewModel.recoveryPassword(email)
        },
        onBack = onBack,
        modifier = modifier
    )
}


@Composable
private fun ValidateEmailContent(
    type: ValidateEmailType,
    isLoading: Boolean,
    error: String?,
    onRecoveryPassword: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {

    FormScreen(
        data = type.toUI(),
        isLoading = isLoading,
        error = error,
        onClick = { email->
            onRecoveryPassword(email)
        },
        onBack = onBack,
        modifier = modifier
    )
}

@Preview
@Composable
fun ValidateEmailScreenPreview() {
    PokedexTheme {
        ValidateEmailContent(
            ValidateEmailType.UPDATE,
            false,
            null,
            {},
            {}
        )
    }
}