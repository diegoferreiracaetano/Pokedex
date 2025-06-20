package com.diegoferreiracaetano.pokedex.ui.components.button // Use o seu pacote correto

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.theme.GrayDisabledBackground
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import com.diegoferreiracaetano.pokedex.ui.theme.White
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AppSwitcher(
    isChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var switchChecked by remember { mutableStateOf(isChecked) }

    val customSwitchColors = SwitchDefaults.colors(
        checkedThumbColor = MaterialTheme.colorScheme.background,
        checkedTrackColor = MaterialTheme.colorScheme.primary,
        uncheckedThumbColor = MaterialTheme.colorScheme.background,
        uncheckedTrackColor = GrayDisabledBackground,
    )

    Switch(
        modifier = modifier,
        checked = switchChecked,
        onCheckedChange = {
            switchChecked = it
            onCheckedChange(switchChecked)
        },
        colors = customSwitchColors
    )
}

@Preview()
@Composable
fun AppSwitchesPreview() {
    PokedexTheme {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AppSwitcher(true)
            AppSwitcher(false)
        }
    }
}