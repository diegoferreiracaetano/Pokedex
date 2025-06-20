package com.diegoferreiracaetano.pokedex.ui.screens.password

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.diegoferreiracaetano.pokedex.domain.user.PasswordValidator
import com.diegoferreiracaetano.pokedex.ui.components.templantes.FormData
import com.diegoferreiracaetano.pokedex.ui.components.templantes.FormScreen
import com.diegoferreiracaetano.pokedex.ui.components.textfield.TextFieldType
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_update
import pokedex.composeapp.generated.resources.change_password_description
import pokedex.composeapp.generated.resources.change_password_label
import pokedex.composeapp.generated.resources.change_password_subtitle
import pokedex.composeapp.generated.resources.change_password_support
import pokedex.composeapp.generated.resources.title_change_password

private val form = FormData(
    Res.string.title_change_password,
    Res.string.change_password_subtitle,
    Res.string.change_password_description,
    Res.string.change_password_label,
    Res.string.change_password_support,
    Res.string.action_update,
    TextFieldType.PASSWORD,
    PasswordValidator,
    Res.string.change_password_label,
)

@Composable
fun ChangePasswordScreen(
    onFinish: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChangePasswordViewModel = koinInject()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ChangePasswordScreenContent(
        isLoading = uiState.isLoading,
        onBack = onBack,
        onClick = { value->
            viewModel.changePassword(value)
        },
        onFinish =  if (uiState.success == true) onFinish else null,
        modifier = modifier
    )
}

@Composable
private fun ChangePasswordScreenContent(
    isLoading: Boolean,
    onBack: () -> Unit,
    onClick: (String) -> Unit,
    onFinish: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {

    FormScreen(
        isLoading = isLoading,
        data = form,
        onBack = onBack,
        onClick = onClick,
        onFinish = onFinish,
        modifier = modifier
    )
}

@Preview
@Composable
fun ChangePasswordScreenPreview() {
    PokedexTheme {
        ChangePasswordScreenContent(
            false,
            {},
            {}
        )
    }
}