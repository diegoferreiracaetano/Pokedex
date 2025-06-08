package com.diegoferreiracaetano.pokedex.ui.screens.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType.*
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountValidator
import com.diegoferreiracaetano.pokedex.domain.user.EmailValidator
import com.diegoferreiracaetano.pokedex.domain.user.NameValidator
import com.diegoferreiracaetano.pokedex.domain.user.PasswordValidator
import com.diegoferreiracaetano.pokedex.ui.components.button.AppButton
import com.diegoferreiracaetano.pokedex.ui.components.textfield.AppTextField
import com.diegoferreiracaetano.pokedex.ui.components.textfield.TextFieldType
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
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
import pokedex.composeapp.generated.resources.title_email
import pokedex.composeapp.generated.resources.title_name
import pokedex.composeapp.generated.resources.title_password


data class CreateAccountData(
    val titleRes: StringResource,
    val subtitleRes: StringResource,
    val placeholderRes: StringResource,
    val supportingTextRes: StringResource,
    val buttonTextRes: StringResource,
    val fieldType: TextFieldType,
    val validator: CreateAccountValidator
)


fun CreateAccountStepType.toUI() = when (this) {
    EMAIL -> CreateAccountData(
        Res.string.create_email_getting_starting,
        Res.string.create_email_title,
        Res.string.title_email,
        Res.string.create_email_label,
        Res.string.action_continue,
        TextFieldType.Email,
        EmailValidator
    )
    PASSWORD -> CreateAccountData(
        Res.string.create_password_getting_starting,
        Res.string.create_password_title,
        Res.string.title_password,
        Res.string.create_password_label,
        Res.string.action_continue,
        TextFieldType.Password,
        PasswordValidator
    )
    NAME -> CreateAccountData(
        Res.string.create_name_getting_starting,
        Res.string.create_name_title,
        Res.string.title_name,
        Res.string.create_name_label,
        Res.string.create_account,
        TextFieldType.None,
        NameValidator
    )
}

@Composable
fun CreateAccountStep(
    step: CreateAccountData,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(step.titleRes),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = stringResource(step.subtitleRes),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = step.placeholderRes,
            isError = isError,
            type = step.fieldType,
            supportingText = stringResource(step.supportingTextRes),
            modifier = Modifier
                .focusRequester(focusRequester)
        )

        Spacer(modifier = Modifier.weight(0.1F))

        AppButton(
            text = stringResource(step.buttonTextRes),
            enabled = value.isNotEmpty() && !isError,
            onClick = onButtonClick
        )
    }
}

@Preview
@Composable
fun CreateAccountStepPreview() {
    PokedexTheme {
        CreateAccountStep(
            EMAIL.toUI(),
            "",
            {},
            false,
            {}
        )
    }
}