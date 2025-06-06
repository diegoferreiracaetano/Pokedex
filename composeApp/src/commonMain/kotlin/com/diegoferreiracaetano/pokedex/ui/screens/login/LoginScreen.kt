package com.diegoferreiracaetano.pokedex.ui.screens.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.StringAnnotation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.components.button.AppButton
import com.diegoferreiracaetano.pokedex.ui.components.button.ButtonType
import com.diegoferreiracaetano.pokedex.ui.screens.login.AuthScreenType.*
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_back
import pokedex.composeapp.generated.resources.already_have_account
import pokedex.composeapp.generated.resources.apple
import pokedex.composeapp.generated.resources.auth_continue_with_apple
import pokedex.composeapp.generated.resources.auth_continue_with_email
import pokedex.composeapp.generated.resources.auth_continue_with_google
import pokedex.composeapp.generated.resources.auth_description
import pokedex.composeapp.generated.resources.character_lusamine
import pokedex.composeapp.generated.resources.character_red
import pokedex.composeapp.generated.resources.create_account
import pokedex.composeapp.generated.resources.google
import pokedex.composeapp.generated.resources.image3
import pokedex.composeapp.generated.resources.login_title
import pokedex.composeapp.generated.resources.login_toolbar_title
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
                toolbarTitle = Res.string.login_toolbar_title,
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
fun OnboardingFinishScreen(
    type: AuthScreenType = LOGIN,
    onCreateAccount: () -> Unit,
    onLogin: () -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val screenData = AuthScreenDataProvider(type)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(screenData.toolbarTitle),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.action_back)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        LoginContent(screenData,{}, {}, Modifier.padding(innerPadding))
    }
}

@Composable
fun LoginContent(
    authScreenData: AuthScreenData,
    onCreateAccount: () -> Unit,
    onLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.1f))

        Image(
            painter = painterResource(authScreenData.imageRes),
            contentDescription = stringResource(authScreenData.title),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(48.dp))

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

        Spacer(modifier = Modifier.weight(1f))

        AppButton(
            image = Res.drawable.apple,
            text = stringResource(Res.string.auth_continue_with_apple),
            type = ButtonType.SECONDARY,
            onClick = onLogin
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            image = Res.drawable.google,
            text = stringResource(Res.string.auth_continue_with_google),
            type = ButtonType.SECONDARY,
            onClick = onLogin
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = stringResource(Res.string.auth_continue_with_email),
            onClick = onCreateAccount
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun OnboardingFinishScreenPreview() {
    PokedexTheme {
        OnboardingFinishScreen(LOGIN,{}, {})
    }
}
