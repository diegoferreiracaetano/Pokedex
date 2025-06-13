package com.diegoferreiracaetano.pokedex.ui.screens.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType.EMAIL
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType.NAME
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType.PASSWORD
import com.diegoferreiracaetano.pokedex.domain.user.EmailValidator
import com.diegoferreiracaetano.pokedex.domain.user.NameValidator
import com.diegoferreiracaetano.pokedex.domain.user.PasswordValidator
import com.diegoferreiracaetano.pokedex.ui.components.templantes.FormData
import com.diegoferreiracaetano.pokedex.ui.components.templantes.FormScreen
import com.diegoferreiracaetano.pokedex.ui.components.textfield.TextFieldType
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import com.diegoferreiracaetano.pokedex.util.getLogger
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_continue
import pokedex.composeapp.generated.resources.create_account
import pokedex.composeapp.generated.resources.create_email_getting_starting
import pokedex.composeapp.generated.resources.create_email_label
import pokedex.composeapp.generated.resources.create_email_title
import pokedex.composeapp.generated.resources.create_name_getting_starting
import pokedex.composeapp.generated.resources.create_name_label
import pokedex.composeapp.generated.resources.create_name_title
import pokedex.composeapp.generated.resources.create_password_getting_starting
import pokedex.composeapp.generated.resources.create_password_label
import pokedex.composeapp.generated.resources.create_password_title
import pokedex.composeapp.generated.resources.signup_toolbar_title
import pokedex.composeapp.generated.resources.title_email
import pokedex.composeapp.generated.resources.title_name
import pokedex.composeapp.generated.resources.title_password

fun CreateAccountStepType.toUI() = when (this) {
    EMAIL -> FormData(
        Res.string.signup_toolbar_title,
        Res.string.create_email_getting_starting,
        Res.string.create_email_title,
        Res.string.title_email,
        Res.string.create_email_label,
        Res.string.action_continue,
        TextFieldType.EMAIL,
        EmailValidator
    )
    PASSWORD -> FormData(
        Res.string.signup_toolbar_title,
        Res.string.create_password_getting_starting,
        Res.string.create_password_title,
        Res.string.title_password,
        Res.string.create_password_label,
        Res.string.action_continue,
        TextFieldType.PASSWORD,
        PasswordValidator
    )
    NAME -> FormData(
        Res.string.signup_toolbar_title,
        Res.string.create_name_getting_starting,
        Res.string.create_name_title,
        Res.string.title_name,
        Res.string.create_name_label,
        Res.string.create_account,
        TextFieldType.NONE,
        NameValidator
    )
}

@Composable
fun CreateAccountScreen(
    step: CreateAccountStepType,
    onNext: (CreateAccountStepType) -> Unit,
    onFinish: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateAccountViewModel = koinInject()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    CreateAccountScreenContent(
        isLoading = uiState.isLoading,
        data = step.toUI(),
        onBack = onBack,
        onButtonClick = { value->
            viewModel.saveStep(step, value)
            step.next()?.let { nextStep ->
                onNext(nextStep)
            }
        },
        onFinishButton =  if (uiState.success == true) onFinish else null,
        modifier = modifier
    )
}

@Composable
private fun CreateAccountScreenContent(
    isLoading: Boolean,
    data: FormData,
    onBack: () -> Unit,
    onButtonClick: (String) -> Unit,
    onFinishButton: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {

    getLogger().d("TESTE CreateAccountScreenContent", onFinishButton.toString())

    FormScreen(
        isLoading = isLoading,
        data = data,
        onBack = onBack,
        onButtonClick = onButtonClick,
        onFinishButton= onFinishButton,
        modifier = modifier
    )
}

@Preview
@Composable
fun CreateAccountScreenPreview() {
    PokedexTheme {
        CreateAccountScreenContent(
            false,
            EMAIL.toUI(),
            {},
            {},
        )
    }
}