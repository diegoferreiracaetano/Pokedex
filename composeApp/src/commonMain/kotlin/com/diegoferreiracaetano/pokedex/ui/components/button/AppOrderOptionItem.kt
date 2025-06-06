package com.diegoferreiracaetano.pokedex.ui.components.button // Certifique-se de usar seu pacote correto

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.theme.Black500
import com.diegoferreiracaetano.pokedex.ui.theme.Black800
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import com.diegoferreiracaetano.pokedex.ui.theme.White
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OrderOptionItem(
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    backgroundColor: Color? = null,
    textColor: Color? = null
) {

    val backgroundColor = backgroundColor?: if (isSelected) {
        Black500
    } else {
        Black800
    }

    val textColor = textColor?: White

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .background(color = backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            color = textColor,
            style = typography.labelLarge
        )
        Icon(
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = "Selecionar opção",
            tint = textColor
        )
    }
}

@Composable
fun OrderSelector(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    backgroundColor: Color? = null,
    textColor: Color? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            options.forEach { option ->
                OrderOptionItem(
                    text = option,
                    backgroundColor = backgroundColor,
                    textColor = textColor,
                    isSelected = (option == selectedOption),
                    onClick = { onOptionSelected(option) }
                )
            }
        }
    }
}

@Preview()
@Composable
fun OrderSelectorPreview() {
    PokedexTheme {
        val orderOptions = listOf("Menor número", "Maior número", "A-Z", "Z-A")
        var currentSelectedOption by remember { mutableStateOf(orderOptions.first()) }


        OrderSelector(
            options = orderOptions,
            selectedOption = currentSelectedOption,
            onOptionSelected = { option ->
                currentSelectedOption = option
                println("Opção selecionada: $option")
            }
        )
    }
}