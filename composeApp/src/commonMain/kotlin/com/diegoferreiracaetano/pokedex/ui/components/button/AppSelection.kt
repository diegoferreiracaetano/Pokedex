import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.color
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.label
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.pokemonList
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import com.diegoferreiracaetano.pokedex.ui.util.contrastTextColor
import org.jetbrains.compose.ui.tooling.preview.Preview

data class AppSelectionOption(
    val label: String,
    val color: Color,
)

@Composable
fun AppSelectionSimple(
    modifier: Modifier = Modifier,
    list: List<String>,
    selected: (Int, AppSelectionOption) -> Unit,
    color: Color = MaterialTheme.colorScheme.onBackground
) {

    AppSelection(
        modifier = modifier,
        list = list.map { AppSelectionOption(it, color) },
        selected = selected
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSelection(
    modifier: Modifier = Modifier,
    list: List<AppSelectionOption>,
    selected: (Int, AppSelectionOption) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(list.first()) }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.background)
    ) {
        TextField(
            onValueChange = {  },
            value = selectedOption.label,
            readOnly = true,
            maxLines = 1,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedTextColor = selectedOption.color.contrastTextColor(),
                unfocusedTextColor = selectedOption.color.contrastTextColor(),
                disabledTextColor = selectedOption.color.contrastTextColor(),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTrailingIconColor = selectedOption.color.contrastTextColor(),
                unfocusedTrailingIconColor = selectedOption.color.contrastTextColor(),
                disabledTrailingIconColor = selectedOption.color.contrastTextColor(),
                focusedContainerColor = selectedOption.color,
                unfocusedContainerColor = selectedOption.color,
                disabledContainerColor = selectedOption.color
            ),
            textStyle = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = selectedOption.color.contrastTextColor()
            ),
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable)
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            list.forEachIndexed { int, option ->
                DropdownMenuItem(
                    modifier = Modifier
                        .background(option.color)
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                    text = {
                        Text(
                            text = option.label,
                            style = MaterialTheme.typography.labelMedium,
                        )
                    },
                    onClick = {
                        selectedOption = option
                        expanded = false
                        selected(int, option)
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = option.color.contrastTextColor(),
                        leadingIconColor = option.color.contrastTextColor(),
                        trailingIconColor = option.color.contrastTextColor(),
                    ),
                    contentPadding = PaddingValues(0.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun AppSelectionDropdownPreview() {
    PokedexTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {

            AppSelectionSimple(
                list =  listOf("Menor número", "Maior número"),
                selected = { index, selected ->
                    println("Opção selecionada: $selected")
                }
            )

            AppSelectionSimple(
                list =  listOf("A-Z", "Z-A"),
                selected = { index,  selected ->
                    println("Opção selecionada: $selected")
                }
            )

            AppSelection(
                list = pokemonList.map {
                    AppSelectionOption(it.label(),pokemonList.first().color)
                },
                selected = { index, selected ->
                    println("Opção selecionada: $selected")
                }
            )
        }
    }
}