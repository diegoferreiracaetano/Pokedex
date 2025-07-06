import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.components.image.AppImage
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.test.Evolution
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.test.PokemonDetail

// --- 3. Componente: PokemonInfoSection ---
@Composable
fun PokemonInfoName(bulbasaur: PokemonDetail) {
    Column(modifier = Modifier) {
        Text(
            text = bulbasaur.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = bulbasaur.number,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

// --- 3. Componente: PokemonInfoSection ---
@Composable
fun PokemonInfoSection(bulbasaur: PokemonDetail) {
    Column(modifier = Modifier) {
        // Seção de Tipos
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            bulbasaur.types.forEach { type ->
                Chip(text = type, backgroundColor = getTypeColor(type))
            }
        }

        // Descrição
        Text(
            text = bulbasaur.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

// --- 4. Componente: PokemonDetailsSection ---
@Composable
fun PokemonDetailsSection(bulbasaur: PokemonDetail) {
    Column(modifier = Modifier) {
        DetailRow(title = "PESO", value = bulbasaur.weight)
        DetailRow(title = "ALTURA", value = bulbasaur.height)
        DetailRow(title = "CATEGORIA", value = bulbasaur.category)
        DetailRow(title = "HABILIDADE", value = bulbasaur.ability)
    }
}

// --- 5. Componente: PokemonGenderSection ---
@Composable
fun PokemonGenderSection(bulbasaur: PokemonDetail) {
    Column(modifier = Modifier) {
        Text(
            text = "GÊNERO",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("♂ ${bulbasaur.genderMaleRatio * 100}%", style = MaterialTheme.typography.bodyMedium)
            Text("♀ ${(1 - bulbasaur.genderMaleRatio) * 100}%", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = bulbasaur.genderMaleRatio,
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp)),
            color = Color.Blue,
            trackColor = Color.Red
        )
    }
}

// --- 6. Componente: PokemonWeaknessesSection ---
@Composable
fun PokemonWeaknessesSection(bulbasaur: PokemonDetail) {
    Column(modifier = Modifier) {
        Text(
            text = "Fraquezas",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            bulbasaur.weaknesses.forEach { weakness ->
                Chip(text = weakness, backgroundColor = getWeaknessColor(weakness))
            }
        }
    }
}

// --- 7. Componente: PokemonEvolutionsSection ---
@Composable
fun PokemonEvolutionsSection(bulbasaur: PokemonDetail) {
    Column(modifier = Modifier) {
        Text(
            text = "Evoluções",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        bulbasaur.evolutions.forEachIndexed { index, evolution ->
            EvolutionItem(evolution = evolution)
            if (index < bulbasaur.evolutions.lastIndex) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Filled.ArrowDownward,
                        contentDescription = "Evolução para o próximo estágio",
                        tint = Color.Gray
                    )
                    evolution.level?.let {
                        Text("Nível $it", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                }
            }
        }
    }
}

// --- 8. Componente: Chip ---
@Composable
fun Chip(text: String, backgroundColor: Color) {
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.wrapContentSize()
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

// --- 9. Componente: DetailRow ---
@Composable
fun DetailRow(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray,
            modifier = Modifier.weight(0.3f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(0.7f)
        )
    }
}

// --- 10. Componente: EvolutionItem ---
@Composable
fun EvolutionItem(evolution: Evolution) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppImage(
            imageURL = evolution.imageUrl,
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(evolution.name, style = MaterialTheme.typography.titleMedium)
            Text(evolution.number, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                evolution.types.forEach { type ->
                    Chip(text = type, backgroundColor = getTypeColor(type, isSmall = true))
                }
            }
        }
    }
}

// --- Funções Utilitárias de Cores (mantidas) ---
fun getTypeColor(type: String, isSmall: Boolean = false): Color {
    return when (type) {
        "Grama" -> Color(0xFF7AC74C)
        "Venenoso" -> Color(0xFFAC4B79)
        "Fogo" -> Color(0xFFEE8130)
        "Psíquico" -> Color(0xFFF95587)
        "Voador" -> Color(0xFFA98FF3)
        "Gelo" -> Color(0xFF96D9D6)
        else -> Color.Gray
    }
}

fun getWeaknessColor(weakness: String): Color {
    return when (weakness) {
        "Fogo" -> Color(0xFFEE8130)
        "Psíquico" -> Color(0xFFF95587)
        "Voador" -> Color(0xFFA98FF3)
        "Gelo" -> Color(0xFF96D9D6)
        else -> Color.Gray
    }
}
