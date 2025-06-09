package com.diegoferreiracaetano.pokedex.ui.screens.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.components.button.AppButton
import com.diegoferreiracaetano.pokedex.ui.components.button.ButtonType
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
import pokedex.composeapp.generated.resources.character_lusamine
import pokedex.composeapp.generated.resources.character_red
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

private object AuthScreenDataProvider {

    operator fun invoke(type: AuthScreenType): AuthScreenData {
        return when (type) {
            LOGIN -> AuthScreenData(
                toolbarTitle = Res.string.action_enter,
                title = Res.string.login_title,
                description = Res.string.auth_description,
                imageRes = Res.drawable.character_lusamine
            )
            SIGN_UP -> AuthScreenData(
                toolbarTitle = Res.string.signup_toolbar_title,
                title = Res.string.signup_title,
                description = Res.string.auth_description,
                imageRes = Res.drawable.character_red
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreLoginScreen(
    type: AuthScreenType = LOGIN,
    onBack: () -> Unit,
    onAccountCreated: () -> Unit,
    modifier: Modifier = Modifier
) {

    val screenData = AuthScreenDataProvider(type)

    AppTopBar(
        stringResource(screenData.toolbarTitle),
        onBack,
        modifier,
    ) { modifier ->
        PreLoginContent(
            screenData,
            onAccountCreated, // change to create account
            {},
            {},
            modifier
        )
    }
}

@Composable
fun PreLoginContent(
    authScreenData: AuthScreenData,
    onCreateAccount: () -> Unit,
    onCreateAccountApple: () -> Unit,
    onCreateAccountGoogle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(0.3f))

        Image(
            painter = painterResource(authScreenData.imageRes),
            contentDescription = stringResource(authScreenData.title),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.weight(0.7f))

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

        Spacer(modifier = Modifier.height(8.dp))

        AppButton(
            image = Res.drawable.apple,
            text = stringResource(Res.string.auth_continue_with_apple),
            type = ButtonType.TERTIARY,
            onClick = onCreateAccountApple
        )

        Spacer(modifier = Modifier.height(8.dp))

        AppButton(
            image = Res.drawable.google,
            text = stringResource(Res.string.auth_continue_with_google),
            type = ButtonType.TERTIARY,
            onClick = onCreateAccountGoogle
        )

        Spacer(modifier = Modifier.height(8.dp))

        AppButton(
            text = stringResource(Res.string.auth_continue_with_email),
            onClick = onCreateAccount
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview
@Composable
fun OnboardingFinishScreenPreview() {
    PokedexTheme {
        PreLoginScreen(SIGN_UP,{}, {})
    }
}
