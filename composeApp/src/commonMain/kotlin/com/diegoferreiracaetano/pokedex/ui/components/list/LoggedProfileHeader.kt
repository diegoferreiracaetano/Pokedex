package com.diegoferreiracaetano.pokedex.ui.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.account_info_title
import pokedex.composeapp.generated.resources.account_password_hidden
import pokedex.composeapp.generated.resources.image1
import pokedex.composeapp.generated.resources.title_email
import pokedex.composeapp.generated.resources.title_name
import pokedex.composeapp.generated.resources.title_password

@Composable
fun LoggedProfileHeader(
    name: String,
    email: String,
    onNameClick: () -> Unit = {},
    onEmailClick: () -> Unit = {},
    onPasswordClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(bottom = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painterResource(Res.drawable.image1),
                contentDescription = null,
                modifier = Modifier.size(48.dp).clip(CircleShape))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge
            )
        }

        SectionTitle(Res.string.account_info_title)
        TextRow(
            Res.string.title_name,
             name,
            onClick = onNameClick
        )
        TextRow(
            Res.string.title_email,
            email,
            onClick = onEmailClick
        )
        TextRow(
            Res.string.title_password,
            Res.string.account_password_hidden,
            onClick = onPasswordClick
        )
    }
}

@Preview
@Composable
fun LoggedProfileHeaderPreview() {
    PokedexTheme {
        LoggedProfileHeader(
            modifier = Modifier.padding(16.dp),
            name = "Diego", email = "diego@gmail.com"
        )
    }
}
