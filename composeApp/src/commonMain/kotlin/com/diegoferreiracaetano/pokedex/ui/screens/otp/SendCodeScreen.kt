package com.diegoferreiracaetano.pokedex.ui.screens.otp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.diegoferreiracaetano.pokedex.ui.components.templantes.FeedbackScreen
import com.diegoferreiracaetano.pokedex.ui.components.loading.ScreenLoading
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppContainer
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppTopBar
import com.diegoferreiracaetano.pokedex.ui.components.textfield.AppOtpVerification
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import com.diegoferreiracaetano.pokedex.ui.util.toAnnotatedStringWithStyledPlaceholder
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_continue
import pokedex.composeapp.generated.resources.forgot_password_title
import pokedex.composeapp.generated.resources.password_reset_description
import pokedex.composeapp.generated.resources.password_reset_title
import pokedex.composeapp.generated.resources.send_code_subtitle
import pokedex.composeapp.generated.resources.send_code_title
import pokedex.composeapp.generated.resources.image5

@Composable
fun SendCodeScreen(
    contact: String,
    onFinish: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SendCodeViewModel = koinInject()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SendCodeScreenContent(
        contact = contact,
        isSuccess = uiState.success != null,
        isLoading = uiState.isLoading,
        messageError = uiState.error,
        onSendCode = { code ->
            viewModel.sendCode(contact, code)
        },
        onFinish = onFinish,
        onBack = onBack,
        modifier = modifier
    )
}

@Composable
private fun SendCodeScreenContent(
    contact: String,
    isSuccess: Boolean,
    isLoading: Boolean,
    messageError: String? = null,
    onSendCode: (String) -> Unit,
    onFinish: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {

    if (isLoading) {
        ScreenLoading(modifier, true)
    } else if (isSuccess) {
        FeedbackScreen(
            title = Res.string.password_reset_title,
            description = Res.string.password_reset_description,
            imageRes = Res.drawable.image5,
            buttonText = Res.string.action_continue,
            onClick = onFinish,
            modifier = modifier
        )
    }
    else {

        val focusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current
        var otpValue by remember { mutableStateOf("") }

        AppContainer(
            topBar = AppTopBar(
                title = stringResource(Res.string.forgot_password_title),
                onBack,
            ),
            modifier = modifier
        ) { padding->

            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
                keyboardController?.show()
            }

            Column(
                modifier = padding.imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(Res.string.send_code_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal
                )

                val rawString = stringResource(Res.string.send_code_subtitle)

                Text(
                    text = rawString.toAnnotatedStringWithStyledPlaceholder(
                        placeholder = contact,
                        style = SpanStyle(fontWeight = FontWeight.Bold)
                    ),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppOtpVerification(
                    otpText = otpValue,
                    isError = !messageError.isNullOrBlank(),
                    errorText = messageError,
                    onOtpTextChange = { text, isComplete ->
                        otpValue = text
                        if (isComplete) {
                            keyboardController?.hide()
                            otpValue = ""
                            onSendCode(text)
                        }
                    },
                    onResendClick = {
                        otpValue = ""
                    },
                    modifier = Modifier
                        .focusRequester(focusRequester)

                )
            }
        }
    }
}



@Preview
@Composable
fun SendCodeScreenContentPreview() {
    PokedexTheme {
        SendCodeScreenContent(
            "diego@gmail.com",
            false,
            false,
            null,
            {},
            {},
            {}
        )
    }
}