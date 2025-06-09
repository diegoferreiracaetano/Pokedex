package com.diegoferreiracaetano.pokedex.ui.screens.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType
import com.diegoferreiracaetano.pokedex.domain.user.getValidationError
import com.diegoferreiracaetano.pokedex.ui.components.feedback.FeedbackScreen
import com.diegoferreiracaetano.pokedex.ui.components.loading.ScreenLoading
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppTopBar
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_continue
import pokedex.composeapp.generated.resources.create_account
import pokedex.composeapp.generated.resources.feedback_description
import pokedex.composeapp.generated.resources.feedback_title
import pokedex.composeapp.generated.resources.register_success

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
        isFinished = uiState.success == true,
        step = step,
        onFinish = onFinish,
        onBack = onBack,
        onButtonClick = { value->
            viewModel.saveStep(step, value)
            step.next()?.let { nextStep ->
                onNext(nextStep)
            }
        },
        modifier = modifier
    )
}

@Composable
private fun CreateAccountScreenContent(
    isLoading: Boolean,
    isFinished: Boolean,
    step: CreateAccountStepType,
    onFinish: () -> Unit,
    onBack: () -> Unit,
    onButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    when {
        isLoading -> {
            ScreenLoading(modifier)
        }
        isFinished -> {

            FeedbackScreen(
                title = Res.string.feedback_title,
                description = Res.string.feedback_description,
                imageRes = Res.drawable.register_success,
                buttonText = Res.string.action_continue,
                onClick = onFinish,
                modifier = modifier
            )
        }
        else -> {

            var fieldValue by remember { mutableStateOf("") }
            var isError by remember { mutableStateOf(false) }

            AppTopBar(
                stringResource(Res.string.create_account),
                onBack = onBack,
                modifier = modifier
            ) { padding->
                isError = step.getValidationError(fieldValue)

                CreateAccountStep(
                    step = step.toUI(),
                    value = fieldValue,
                    onValueChange = { fieldValue = it },
                    isError = isError,
                    onButtonClick = { onButtonClick(fieldValue) },
                    modifier = padding
                )
            }
        }
    }
}

@Preview
@Composable
fun CreateAccountScreenPreview() {
    PokedexTheme {
        CreateAccountScreenContent(
            false,
            false,
            CreateAccountStepType.EMAIL,
            {},
            {},
            {}
        )
    }
}