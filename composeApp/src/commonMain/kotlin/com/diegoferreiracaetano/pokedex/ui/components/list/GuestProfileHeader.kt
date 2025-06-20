package com.diegoferreiracaetano.pokedex.ui.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.components.button.AppButonSecondary
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_enter_or_register
import pokedex.composeapp.generated.resources.image1
import pokedex.composeapp.generated.resources.profile_description

@Composable
fun GuestProfileHeader(
    title: StringResource = Res.string.profile_description,
    textButton: StringResource = Res.string.action_enter_or_register,
    image: DrawableResource = Res.drawable.image1,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(
                stringResource(title),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
        }
        AppButonSecondary(
            text = stringResource(textButton),
            onClick = onClick,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview
@Composable
private fun GuestProfileHeaderPreview() {
    PokedexTheme {
        GuestProfileHeader {}
    }
}