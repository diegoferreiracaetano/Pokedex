package com.diegoferreiracaetano.pokedex.ui.screens.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.diegoferreiracaetano.pokedex.ui.components.alert.showSnackBarError
import com.diegoferreiracaetano.pokedex.ui.components.alert.showSnackBarSuccess
import com.diegoferreiracaetano.pokedex.ui.components.button.AppButton
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppContainer
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppTopBar
import com.diegoferreiracaetano.pokedex.ui.components.textfield.AppTextField
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_update
import pokedex.composeapp.generated.resources.title_change_name
import pokedex.composeapp.generated.resources.title_full_name_user
import pokedex.composeapp.generated.resources.title_name_user

@Composable
fun ChangeUserNameScreen(
    onBack: () ->Unit,
    onFinish: () ->Unit,
    modifier: Modifier = Modifier,
    viewModel: ChangeUserNameViewModel = koinInject()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val success = if(uiState.success == true) "tetse" else null

    ChangeUserNameContent(
        onBack = onBack,
        error = uiState.error,
        success = success,
        onChangeUserName = { name, fullName ->
            viewModel.changeUserName(name, fullName)
        },
        modifier = modifier
    )

}

@Composable
fun ChangeUserNameContent(
    onBack: () -> Unit,
    onChangeUserName: (String, String) -> Unit,
    error: String? = null,
    success: String? = null,
    modifier: Modifier = Modifier
) {

    val snackBarHostState = remember { SnackbarHostState() }

    AppContainer(
        topBar = AppTopBar(
            title = stringResource(Res.string.title_change_name),
            onBack,
        ),
        snackBarHostState = snackBarHostState,
        modifier = modifier
    ) { modifier ->

        var nameValue by remember { mutableStateOf("") }
        var fullNameValue by remember { mutableStateOf("") }
        var isNameError by remember { mutableStateOf(false) }
        val focusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current


        LaunchedEffect(Unit) {
            if(error.isNullOrEmpty() && success == null) {
                focusRequester.requestFocus()
                keyboardController?.show()
            }
        }

        if(!error.isNullOrEmpty()) {
            snackBarHostState.showSnackBarError(error)
        }

        if (success != null) {
            snackBarHostState.showSnackBarSuccess(success)
        }

        Column(
            modifier = modifier.imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AppTextField(
                value = nameValue,
                title = Res.string.title_name_user,
                onValueChange = { nameValue = it },
                placeholder = Res.string.title_name_user,
                isError = isNameError,
                modifier = Modifier
                    .focusRequester(focusRequester)
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppTextField(
                value = fullNameValue,
                title = Res.string.title_full_name_user,
                onValueChange = { fullNameValue = it },
                placeholder = Res.string.title_full_name_user,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.weight(0.8f))

            AppButton(
                text = stringResource(Res.string.action_update),
                enabled = nameValue.isNotEmpty() && !isNameError,
                onClick = {
                    keyboardController?.hide()
                    onChangeUserName(nameValue, fullNameValue)
                }
            )
        }
    }
}

@Preview
@Composable
fun ChangeUserNamePreview() {
    PokedexTheme {
        ChangeUserNameContent({}, { _, _ -> })
    }
}