package com.diegoferreiracaetano.pokedex.ui.screens.forgotPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType
import com.diegoferreiracaetano.pokedex.domain.user.getValidationError
import com.diegoferreiracaetano.pokedex.ui.components.button.AppButton
import com.diegoferreiracaetano.pokedex.ui.components.loading.ScreenLoading
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppTopBar
import com.diegoferreiracaetano.pokedex.ui.components.textfield.AppTextField
import com.diegoferreiracaetano.pokedex.ui.components.textfield.TextFieldType
import com.diegoferreiracaetano.pokedex.ui.screens.forgotPassword.ChangePasswordType.*
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_continue
import pokedex.composeapp.generated.resources.create_email_title
import pokedex.composeapp.generated.resources.forgot_password_description
import pokedex.composeapp.generated.resources.title_email

@Composable
fun ChangePasswordScreen(
    type: ChangePasswordType,
    onSendCode: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChangePasswordViewModel = koinInject()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.success) {
        uiState.success?.let {
            onSendCode(it)
        }
    }

    ChangePasswordContent(
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
private fun ChangePasswordContent(
    type: ChangePasswordType,
    isLoading: Boolean,
    error: String?,
    onRecoveryPassword: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {

    if (isLoading) {
        ScreenLoading(modifier, true)
    }
    else {

        var emailValue by remember { mutableStateOf("") }
        var isEmailError by remember { mutableStateOf(false) }
        val snackbarHostState = remember { SnackbarHostState() }
        val texts = type.toUI()

        LaunchedEffect(error) {
            if (!error.isNullOrEmpty()) {
                snackbarHostState.showSnackbar(error)
            }
        }

        AppTopBar(
            stringResource(texts.title),
            onBack = onBack,
            snackbarHostState = snackbarHostState,
            modifier = modifier
        ) { padding->
            isEmailError = CreateAccountStepType.EMAIL.getValidationError(emailValue)

            val focusRequester = remember { FocusRequester() }
            val keyboardController = LocalSoftwareKeyboardController.current

            LaunchedEffect(Unit) {
                if(error.isNullOrEmpty()) {
                    focusRequester.requestFocus()
                    keyboardController?.show()
                }
            }

            Column(
                modifier = padding
                    .fillMaxSize()
                    .background(colorScheme.surface)
                    .imePadding()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(0.15f))

                Text(
                    text = stringResource(Res.string.create_email_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = stringResource(texts.subtitle),
                    style = MaterialTheme.typography.titleLarge,
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppTextField(
                    value = emailValue,
                    onValueChange = { emailValue = it },
                    placeholder = Res.string.title_email,
                    isError = isEmailError,
                    type = TextFieldType.Email,
                    supportingText = Res.string.forgot_password_description,
                    modifier = Modifier
                        .focusRequester(focusRequester)
                )

                Spacer(modifier = Modifier.weight(0.8f))

                AppButton(
                    text = stringResource(Res.string.action_continue),
                    enabled =
                        (emailValue.isNotEmpty() && !isEmailError),
                    onClick = { onRecoveryPassword(emailValue) }
                )
            }
        }
    }
}

@Preview
@Composable
fun ChangePasswordScreenPreview() {
    PokedexTheme {
        ChangePasswordContent(
            UPDATE,
            false,
            null,
            {},
            {}
        )
    }
}