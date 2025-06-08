package com.diegoferreiracaetano.pokedex.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.image3
import org.jetbrains.compose.resources.stringResource
import pokedex.composeapp.generated.resources.skip
import pokedex.composeapp.generated.resources.onboarding_image_content_description
import pokedex.composeapp.generated.resources.onboarding_title
import pokedex.composeapp.generated.resources.onboarding_subtitle
import pokedex.composeapp.generated.resources.create_account
import pokedex.composeapp.generated.resources.already_have_account

@Composable
fun OnboardingFinishScreen(
    onCreateAccount: () -> Unit,
    onLogin: () -> Unit,
    onSkip: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.1f))

        Text(
            text = stringResource(Res.string.skip),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onSkip),
            textAlign = TextAlign.End
        )

        Image(
            painter = painterResource(Res.drawable.image3),
            contentDescription = stringResource(Res.string.onboarding_image_content_description),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = stringResource(Res.string.onboarding_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(Res.string.onboarding_subtitle),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        AppButton(
            text = stringResource(Res.string.create_account),
            onClick = onCreateAccount
        )

        Spacer(modifier = Modifier.height(8.dp))

        AppButton(
            text = stringResource(Res.string.already_have_account),
            type = ButtonType.SECONDARY,
            onClick = onLogin
        )
    }
}

@Preview
@Composable
fun OnboardingFinishScreenPreview() {
    PokedexTheme {
        OnboardingFinishScreen({}, {}, {})
    }
}
