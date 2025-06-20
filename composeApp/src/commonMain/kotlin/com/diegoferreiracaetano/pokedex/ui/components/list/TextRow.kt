package com.diegoferreiracaetano.pokedex.ui.components.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.components.button.AppSwitcher
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.action_back
import pokedex.composeapp.generated.resources.app_name
import pokedex.composeapp.generated.resources.language_ui
import pokedex.composeapp.generated.resources.language_ui_value
import pokedex.composeapp.generated.resources.link_terms
import pokedex.composeapp.generated.resources.link_terms_description
import pokedex.composeapp.generated.resources.mega_evolutions
import pokedex.composeapp.generated.resources.mega_evolutions_description
import pokedex.composeapp.generated.resources.row_version
import pokedex.composeapp.generated.resources.row_version_value

@Composable
fun SectionTitle(
    title: StringResource
) {
    Text(
        text = stringResource(title),
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp, bottom = 8.dp)
    )
}

@Composable
fun TextRow(
    label: StringResource,
    value: StringResource,
    isEnabled: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    onCheckedChange: (() -> Unit)? = null
) {
    TextRow(
        label = label,
        value = stringResource(value),
        isEnabled = isEnabled,
        modifier = modifier,
        onClick = onClick,
        onCheckedChange = onCheckedChange,
    )
}

@Composable
fun TextRow(
    label: StringResource,
    value: String,
    isEnabled: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    onCheckedChange: (() -> Unit)? = null
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .clickable(onClick = {
                    if (onClick != null) {
                        onClick()
                    }
                })
                .weight(1f),
        ) {

            Text(
                stringResource(label),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                value,
                style = MaterialTheme.typography.labelMedium
            )
        }

        if (onCheckedChange != null) {
            AppSwitcher(
                modifier = Modifier.padding(start = 8.dp),
                isChecked = isEnabled,
                onCheckedChange = { onCheckedChange() }
            )
        } else if (onClick!= null ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = stringResource(Res.string.action_back)
            )
        }
    }
}

@Preview
@Composable
fun SectionTitleAndRowPreview() {
    PokedexTheme {
        Column(
            Modifier.padding(16.dp)
        ) {
            SectionTitle(title = Res.string.app_name)
            TextRow(label = Res.string.language_ui, value = Res.string.language_ui_value)
            TextRow(label = Res.string.link_terms, value = Res.string.link_terms_description, onClick = {})
            TextRow(label = Res.string.mega_evolutions, value = Res.string.mega_evolutions_description, onCheckedChange = {}, isEnabled = true)
        }
    }
}

