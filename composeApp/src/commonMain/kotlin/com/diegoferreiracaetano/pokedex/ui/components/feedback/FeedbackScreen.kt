package com.diegoferreiracaetano.pokedex.ui.components.feedback

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_continue
import pokedex.composeapp.generated.resources.feedback_description
import pokedex.composeapp.generated.resources.feedback_title
import pokedex.composeapp.generated.resources.register_success

@Composable
fun FeedbackScreen(
    title: StringResource,
    description: StringResource,
    imageRes: DrawableResource,
    buttonText: StringResource,
    onClick: () -> Unit,
    imageContentDescription: String? = null,
    modifier: Modifier = Modifier
) {

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f))

        Image(
            painter = painterResource(imageRes),
            contentDescription = imageContentDescription,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio( 1.5f),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = stringResource( title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(description),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        AppButton(
            text = stringResource(buttonText),
            onClick = onClick
        )


        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Preview
@Composable
fun FeedbackScreenPreView() {
    PokedexTheme {
        FeedbackScreen(
            title = Res.string.feedback_title,
            description = Res.string.feedback_description,
            imageRes = Res.drawable.register_success,
            buttonText = Res.string.action_continue,
            onClick =  {}
        )
    }
}