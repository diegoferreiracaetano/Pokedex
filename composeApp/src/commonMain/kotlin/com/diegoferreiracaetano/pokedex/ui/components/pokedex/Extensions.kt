package com.diegoferreiracaetano.pokedex.ui.components.pokedex

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Square
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.diegoferreiracaetano.pokedex.domain.pokedex.PokemonType
import com.diegoferreiracaetano.pokedex.ui.theme.Bug
import com.diegoferreiracaetano.pokedex.ui.theme.Dark
import com.diegoferreiracaetano.pokedex.ui.theme.Dragon
import com.diegoferreiracaetano.pokedex.ui.theme.Electric
import com.diegoferreiracaetano.pokedex.ui.theme.Fairy
import com.diegoferreiracaetano.pokedex.ui.theme.Fighting
import com.diegoferreiracaetano.pokedex.ui.theme.Fire
import com.diegoferreiracaetano.pokedex.ui.theme.Flying
import com.diegoferreiracaetano.pokedex.ui.theme.Ghost
import com.diegoferreiracaetano.pokedex.ui.theme.Grass
import com.diegoferreiracaetano.pokedex.ui.theme.Ground
import com.diegoferreiracaetano.pokedex.ui.theme.Ice
import com.diegoferreiracaetano.pokedex.ui.theme.Normal
import com.diegoferreiracaetano.pokedex.ui.theme.Poison
import com.diegoferreiracaetano.pokedex.ui.theme.Psychic
import com.diegoferreiracaetano.pokedex.ui.theme.Rock
import com.diegoferreiracaetano.pokedex.ui.theme.Steel
import com.diegoferreiracaetano.pokedex.ui.theme.Water
import org.jetbrains.compose.resources.stringResource
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.type_bug
import pokedex.composeapp.generated.resources.type_dark
import pokedex.composeapp.generated.resources.type_dragon
import pokedex.composeapp.generated.resources.type_electric
import pokedex.composeapp.generated.resources.type_fairy
import pokedex.composeapp.generated.resources.type_fighting
import pokedex.composeapp.generated.resources.type_fire
import pokedex.composeapp.generated.resources.type_flying
import pokedex.composeapp.generated.resources.type_ghost
import pokedex.composeapp.generated.resources.type_grass
import pokedex.composeapp.generated.resources.type_ground
import pokedex.composeapp.generated.resources.type_ice
import pokedex.composeapp.generated.resources.type_normal
import pokedex.composeapp.generated.resources.type_poison
import pokedex.composeapp.generated.resources.type_psychic
import pokedex.composeapp.generated.resources.type_rock
import pokedex.composeapp.generated.resources.type_steel
import pokedex.composeapp.generated.resources.type_water

@Composable
fun PokemonType.label(): String = when(this) {
    PokemonType.NORMAL -> stringResource(Res.string.type_normal)
    PokemonType.FIRE -> stringResource(Res.string.type_fire)
    PokemonType.WATER -> stringResource(Res.string.type_water)
    PokemonType.ELECTRIC -> stringResource(Res.string.type_electric)
    PokemonType.GRASS -> stringResource(Res.string.type_grass)
    PokemonType.ICE -> stringResource(Res.string.type_ice)
    PokemonType.FIGHTING -> stringResource(Res.string.type_fighting)
    PokemonType.POISON -> stringResource(Res.string.type_poison)
    PokemonType.GROUND -> stringResource(Res.string.type_ground)
    PokemonType.FLYING -> stringResource(Res.string.type_flying)
    PokemonType.PSYCHIC -> stringResource(Res.string.type_psychic)
    PokemonType.BUG -> stringResource(Res.string.type_bug)
    PokemonType.ROCK -> stringResource(Res.string.type_rock)
    PokemonType.GHOST -> stringResource(Res.string.type_ghost)
    PokemonType.DRAGON -> stringResource(Res.string.type_dragon)
    PokemonType.DARK -> stringResource(Res.string.type_dark)
    PokemonType.STEEL -> stringResource(Res.string.type_steel)
    PokemonType.FAIRY -> stringResource(Res.string.type_fairy)
}

val PokemonType.color: Color
    get() = when(this) {
        PokemonType.NORMAL -> Normal
        PokemonType.FIRE -> Fire
        PokemonType.WATER -> Water
        PokemonType.ELECTRIC -> Electric
        PokemonType.GRASS -> Grass
        PokemonType.ICE -> Ice
        PokemonType.FIGHTING -> Fighting
        PokemonType.POISON -> Poison
        PokemonType.GROUND -> Ground
        PokemonType.FLYING -> Flying
        PokemonType.PSYCHIC -> Psychic
        PokemonType.BUG -> Bug
        PokemonType.ROCK -> Rock
        PokemonType.GHOST -> Ghost
        PokemonType.DRAGON -> Dragon
        PokemonType.DARK -> Dark
        PokemonType.STEEL -> Steel
        PokemonType.FAIRY -> Fairy
    }

val PokemonType.icon: ImageVector
    get() = when(this) {
        PokemonType.NORMAL -> Icons.Default.Circle
        PokemonType.FIRE -> Icons.Default.LocalFireDepartment
        PokemonType.WATER -> Icons.Default.WaterDrop
        PokemonType.ELECTRIC -> Icons.Default.FlashOn
        PokemonType.GRASS -> Icons.Default.Spa
        PokemonType.ICE -> Icons.Default.AcUnit
        PokemonType.FIGHTING -> Icons.Default.FitnessCenter
        PokemonType.POISON -> Icons.Default.Science
        PokemonType.GROUND -> Icons.Default.Landscape
        PokemonType.FLYING -> Icons.Default.Flight
        PokemonType.PSYCHIC -> Icons.Default.Lightbulb
        PokemonType.BUG -> Icons.Default.BugReport
        PokemonType.ROCK -> Icons.Default.Square // Um quadrado genérico para rocha
        PokemonType.GHOST -> Icons.Default.VisibilityOff // Para algo invisível/etéreo
        PokemonType.DRAGON -> Icons.Default.Star // Símbolo comum de fantasia
        PokemonType.DARK -> Icons.Default.DarkMode
        PokemonType.STEEL -> Icons.Default.Build
        PokemonType.FAIRY -> Icons.Default.Palette // Para algo artístico/mágico
    }

val pokemonList = listOf(
    PokemonType.NORMAL,
    PokemonType.FIRE,
    PokemonType.WATER,
    PokemonType.ELECTRIC,
    PokemonType.GRASS,
    PokemonType.ICE,
    PokemonType.FIGHTING,
    PokemonType.POISON,
    PokemonType.GROUND,
    PokemonType.FLYING,
    PokemonType.PSYCHIC ,
    PokemonType.BUG ,
    PokemonType.ROCK ,
    PokemonType.GHOST,
    PokemonType.DRAGON ,
    PokemonType.DARK ,
    PokemonType.STEEL,
    PokemonType.FAIRY
)