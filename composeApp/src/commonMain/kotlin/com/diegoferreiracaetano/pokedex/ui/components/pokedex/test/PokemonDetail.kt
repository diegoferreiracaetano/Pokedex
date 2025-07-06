// PokemonDetailScreen.kt

package com.diegoferreiracaetano.pokedex.ui.components.pokedex.test

import PokemonDetailsSection
import PokemonEvolutionsSection
import PokemonGenderSection
import PokemonInfoSection
import PokemonWeaknessesSection
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diegoferreiracaetano.pokedex.ui.components.image.AppImage
import org.jetbrains.compose.ui.tooling.preview.Preview

// --- Dados de modelo simplificado ---
data class PokemonDetail(
    val name: String,
    val number: String,
    val backgroundColor: Color,
    val imageUrl: String,
    val types: List<String>,
    val description: String,
    val weight: String,
    val height: String,
    val category: String,
    val ability: String,
    val genderMaleRatio: Float,
    val weaknesses: List<String>,
    val evolutions: List<Evolution>
)

data class Evolution(
    val name: String,
    val number: String,
    val imageUrl: String,
    val types: List<String>,
    val level: Int?
)

@Composable
fun PokemonExpandedHeaderContent(
    pokemon: PokemonDetail,
    collapsedFraction: Float,
    backgroundColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppImage(
            imageURL = pokemon.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .alpha(1 - collapsedFraction * 2)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(1 - collapsedFraction * 2)
        ) {
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = (40 - (collapsedFraction * 20)).sp),
                color = Color.White
            )
            Text(
                text = pokemon.number,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}


