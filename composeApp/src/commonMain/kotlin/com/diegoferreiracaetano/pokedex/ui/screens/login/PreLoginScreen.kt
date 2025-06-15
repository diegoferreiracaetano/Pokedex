package com.diegoferreiracaetano.pokedex.ui.screens.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.components.button.AppButton
import com.diegoferreiracaetano.pokedex.ui.components.button.ButtonType
import com.diegoferreiracaetano.pokedex.ui.components.image.CircularImage
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppContainer
import com.diegoferreiracaetano.pokedex.ui.components.navigation.AppTopBar
import com.diegoferreiracaetano.pokedex.ui.screens.login.AuthScreenType.LOGIN
import com.diegoferreiracaetano.pokedex.ui.screens.login.AuthScreenType.SIGN_UP
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_enter
import pokedex.composeapp.generated.resources.apple
import pokedex.composeapp.generated.resources.auth_continue_with_apple
import pokedex.composeapp.generated.resources.auth_continue_with_email
import pokedex.composeapp.generated.resources.auth_continue_with_google
import pokedex.composeapp.generated.resources.auth_description
import pokedex.composeapp.generated.resources.image6
import pokedex.composeapp.generated.resources.image7
import pokedex.composeapp.generated.resources.google
import pokedex.composeapp.generated.resources.login_title
import pokedex.composeapp.generated.resources.signup_title
import pokedex.composeapp.generated.resources.signup_toolbar_title

enum class AuthScreenType {
    LOGIN,
    SIGN_UP
}

data class AuthScreenData(
    val toolbarTitle: StringResource,
    val title: StringResource,
    val description: StringResource,
    val imageRes: DrawableResource
)

private fun AuthScreenType.toUI() = when (this) {
    LOGIN -> AuthScreenData(
        toolbarTitle = Res.string.action_enter,
        title = Res.string.login_title,
        description = Res.string.auth_description,
        imageRes = Res.drawable.image6
    )
    SIGN_UP -> AuthScreenData(
        toolbarTitle = Res.string.signup_toolbar_title,
        title = Res.string.signup_title,
        description = Res.string.auth_description,
        imageRes = Res.drawable.image7
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreLoginScreen(
    type: AuthScreenType = LOGIN,
    onBack: () -> Unit,
    onAccountCreated: () -> Unit,
    modifier: Modifier = Modifier
) {

    val authScreenData = type.toUI()

    AppContainer(
        topBar = AppTopBar(
            title = stringResource(authScreenData.toolbarTitle),
            onBack,
        ),
        modifier = modifier
    ) { modifier ->

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CircularImage(
                resource = authScreenData.imageRes,
                contentDescription = authScreenData.title,
                modifier = Modifier.weight(0.3f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(Res.string.login_title),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(authScreenData.description),
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                image = Res.drawable.apple,
                text = stringResource(Res.string.auth_continue_with_apple),
                type = ButtonType.TERTIARY,
                onClick = {
                    // todo
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                image = Res.drawable.google,
                text = stringResource(Res.string.auth_continue_with_google),
                type = ButtonType.TERTIARY,
                onClick = {
                    // todo
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                text = stringResource(Res.string.auth_continue_with_email),
                onClick = onAccountCreated
            )
        }
    }
}


@Preview
@Composable
fun OnboardingFinishScreenPreview() {
    PokedexTheme {
        PreLoginScreen(SIGN_UP,{}, {})
    }
}
