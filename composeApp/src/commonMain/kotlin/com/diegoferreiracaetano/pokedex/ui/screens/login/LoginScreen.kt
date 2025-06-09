package com.diegoferreiracaetano.pokedex.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.diegoferreiracaetano.pokedex.domain.user.User
import com.diegoferreiracaetano.pokedex.domain.user.getValidationError
import com.diegoferreiracaetano.pokedex.ui.components.button.AppButton
import com.diegoferreiracaetano.pokedex.ui.components.feedback.FeedbackScreen
import com.diegoferreiracaetano.pokedex.ui.components.loading.ScreenLoading
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppTopBar
import com.diegoferreiracaetano.pokedex.ui.components.textfield.AppTextField
import com.diegoferreiracaetano.pokedex.ui.components.textfield.TextFieldType
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_continue
import pokedex.composeapp.generated.resources.action_enter
import pokedex.composeapp.generated.resources.forgot_password
import pokedex.composeapp.generated.resources.login_screen_subtitle
import pokedex.composeapp.generated.resources.login_screen_title
import pokedex.composeapp.generated.resources.login_welcome_description
import pokedex.composeapp.generated.resources.login_welcome_title
import pokedex.composeapp.generated.resources.title_email
import pokedex.composeapp.generated.resources.title_password
import pokedex.composeapp.generated.resources.welcome

@Composable
fun LoginScreen(
    onFinish: () -> Unit,
    onBack: () -> Unit,
    onForgotPassword: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinInject()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreenContent(
        isLoading = uiState.isLoading,
        user = uiState.success,
        error = uiState.error,
        onLogin = { email, password->
            viewModel.login(email, password)
        },
        onFinish = onFinish,
        onBack = onBack,
        onForgotPassword = onForgotPassword,
        modifier = modifier
    )
}

@Composable
private fun LoginScreenContent(
    isLoading: Boolean,
    user: User?,
    error: String?,
    onLogin: (String, String) -> Unit,
    onFinish: () -> Unit,
    onBack: () -> Unit,
    onForgotPassword: () -> Unit,
    modifier: Modifier = Modifier,
) {

    if (isLoading) {
        ScreenLoading(modifier)
    }
    else if (user != null) {
        FeedbackScreen(
            title = Res.string.login_welcome_title,
            description = Res.string.login_welcome_description,
            imageRes = Res.drawable.welcome,
            buttonText = Res.string.action_continue,
            onClick = onFinish,
            modifier = modifier
        )
    }
    else {

        var emailValue by remember { mutableStateOf("") }
        var passwordValue by remember { mutableStateOf("") }
        var isEmailError by remember { mutableStateOf(false) }
        var isPasswordError by remember { mutableStateOf(false) }
        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(error) {
            if (!error.isNullOrEmpty()) {
                snackbarHostState.showSnackbar(error)
            }
        }

        AppTopBar(
            stringResource(Res.string.action_enter),
            onBack = onBack,
            snackbarHostState = snackbarHostState,
            modifier = modifier
        ) { padding->
            isEmailError = CreateAccountStepType.EMAIL.getValidationError(emailValue)
            isPasswordError = CreateAccountStepType.PASSWORD.getValidationError(passwordValue)

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
                    .imePadding()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(0.2f))

                Text(
                    text = stringResource(Res.string.login_screen_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = stringResource(Res.string.login_screen_subtitle),
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppTextField(
                    value = emailValue,
                    onValueChange = { emailValue = it },
                    placeholder = Res.string.title_email,
                    isError = isEmailError,
                    type = TextFieldType.Email,
                    modifier = Modifier
                        .focusRequester(focusRequester)
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppTextField(
                    value = passwordValue,
                    onValueChange = { passwordValue = it },
                    placeholder = Res.string.title_password,
                    isError = isPasswordError,
                    type = TextFieldType.Password,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(Res.string.forgot_password),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable(onClick = onForgotPassword)
                )

                Spacer(modifier = Modifier.weight(0.8f))

                AppButton(
                    text = stringResource(Res.string.action_enter),
                    enabled =
                        (emailValue.isNotEmpty() && !isEmailError) &&
                                (passwordValue.isNotEmpty() && !isPasswordError),
                    onClick = { onLogin(emailValue, passwordValue) }
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    PokedexTheme {
        LoginScreenContent(
            false,
            null,
            null,
            {_, _->},
            {},
            {},
            {}
        )
    }
}