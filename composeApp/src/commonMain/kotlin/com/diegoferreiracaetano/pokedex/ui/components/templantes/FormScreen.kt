package com.diegoferreiracaetano.pokedex.ui.components.templantes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.MaterialTheme
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
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountValidator
import com.diegoferreiracaetano.pokedex.domain.user.EmailValidator
import com.diegoferreiracaetano.pokedex.domain.user.NameValidator
import com.diegoferreiracaetano.pokedex.domain.user.PasswordValidator
import com.diegoferreiracaetano.pokedex.ui.components.button.AppButton
import com.diegoferreiracaetano.pokedex.ui.components.loading.ScreenLoading
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppContainer
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppTopBar
import com.diegoferreiracaetano.pokedex.ui.components.textfield.AppTextField
import com.diegoferreiracaetano.pokedex.ui.components.textfield.TextFieldType
import com.diegoferreiracaetano.pokedex.ui.components.textfield.TextFieldType.EMAIL
import com.diegoferreiracaetano.pokedex.ui.components.textfield.TextFieldType.NONE
import com.diegoferreiracaetano.pokedex.ui.components.textfield.TextFieldType.PASSWORD
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_continue
import pokedex.composeapp.generated.resources.create_email_getting_starting
import pokedex.composeapp.generated.resources.create_email_label
import pokedex.composeapp.generated.resources.create_email_title
import pokedex.composeapp.generated.resources.feedback_description
import pokedex.composeapp.generated.resources.feedback_title
import pokedex.composeapp.generated.resources.image4
import pokedex.composeapp.generated.resources.signup_toolbar_title
import pokedex.composeapp.generated.resources.title_email

data class FormData(
    val topBarTitle: StringResource,
    val titleRes: StringResource,
    val subtitleRes: StringResource,
    val placeholderRes: StringResource,
    val supportingTextRes: StringResource,
    val buttonTextRes: StringResource,
    val fieldType: TextFieldType,
    val validator: CreateAccountValidator,
    val label: StringResource? = null
)

private fun TextFieldType.getValidationError(value: String) = when(this) {
    NONE -> NameValidator.getError(value)
    EMAIL -> EmailValidator.getError(value)
    PASSWORD -> PasswordValidator.getError(value)
}

@Composable
fun FormScreen(
    data: FormData,
    isLoading: Boolean = false,
    error: String? = null,
    onBack: () -> Unit,
    onClick: (String) -> Unit,
    onFinish: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {

    when {
        isLoading -> {
            ScreenLoading(modifier)
        }
        onFinish != null -> {
            FeedbackScreen(
                title = Res.string.feedback_title,
                description = Res.string.feedback_description,
                imageRes = Res.drawable.image4,
                buttonText = Res.string.action_continue,
                onClick = onFinish,
                modifier = modifier
            )
        }
        else -> {

            val snackbarHostState = remember { SnackbarHostState() }
            var fieldValue by remember { mutableStateOf("") }
            var isError by remember { mutableStateOf(false) }

            LaunchedEffect(error) {
                if (!error.isNullOrEmpty()) {
                    snackbarHostState.showSnackbar(error)
                }
            }

            AppContainer(
                topBar = AppTopBar(
                    title = stringResource(data.topBarTitle),
                    onBack = onBack
                ),
                snackBarHostState = snackbarHostState,
                modifier = modifier,
            ) { modifier->
                isError = data.fieldType.getValidationError(fieldValue)

                val focusRequester = remember { FocusRequester() }
                val keyboardController = LocalSoftwareKeyboardController.current

                LaunchedEffect(Unit) {
                    if(error.isNullOrEmpty()) {
                        focusRequester.requestFocus()
                        keyboardController?.show()
                    }
                }

                Column(
                    modifier = modifier.imePadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.weight(0.1F))

                    Text(
                        text = stringResource(data.titleRes),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = stringResource(data.subtitleRes),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    AppTextField(
                        label = data.label,
                        value = fieldValue,
                        onValueChange = { fieldValue = it },
                        placeholder = data.placeholderRes,
                        isError = isError,
                        type = data.fieldType,
                        supportingText = data.supportingTextRes,
                        modifier = Modifier
                            .focusRequester(focusRequester)
                    )


                    Spacer(modifier = Modifier.weight(0.9F))

                    AppButton(
                        text = stringResource(data.buttonTextRes),
                        enabled = fieldValue.isNotEmpty() && !isError,
                        onClick = { onClick(fieldValue) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FormScreenPreview() {
    val formEmail = FormData(
        Res.string.signup_toolbar_title,
        Res.string.create_email_getting_starting,
        Res.string.create_email_title,
        Res.string.title_email,
        Res.string.create_email_label,
        Res.string.action_continue,
        EMAIL,
        EmailValidator
    )

    PokedexTheme {
        FormScreen(
            data = formEmail,
            false,
            error = null,
            {},
            {}
        )
    }
}