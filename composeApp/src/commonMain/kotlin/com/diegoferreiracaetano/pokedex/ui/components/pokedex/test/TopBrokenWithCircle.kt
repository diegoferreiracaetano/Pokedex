package com.diegoferreiracaetano.pokedex.ui.components.pokedex.test

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.diegoferreiracaetano.pokedex.ui.components.image.AppImage
import com.diegoferreiracaetano.pokedex.ui.components.pokedex.typeColor
import com.diegoferreiracaetano.pokedex.ui.theme.Grass
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.ic_leaf

//@Composable
//fun TopBrokenWithCircle(
//    backgroundColor: Color = Color.Green,
//    modifier: Modifier = Modifier,
//) {
//    BoxWithConstraints(
//        modifier = modifier.fillMaxSize()
//    ) {
//        val width = constraints.maxWidth.toFloat()
//        val height = constraints.maxHeight.toFloat()
//        val halfHeight = height / 9f
//
//        val colorGradient = Brush.horizontalGradient(
//            colors = listOf(
//                backgroundColor,
//                backgroundColor.copy(alpha = 0.6f)
//            )
//        )
//
//        Canvas(modifier = Modifier
//            .fillMaxWidth()
//            .height(halfHeight.dp)) {
//            val radius = 200.dp.toPx()
//
//            val rectHeight = halfHeight - radius
//            drawCircle(
//                brush =  colorGradient,
//                radius = width,
//                center = Offset(width / 2, rectHeight)
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PokemonCard(
//    name: String,
//    number: String,
//    imageUrl: String,
//    modifier: Modifier = Modifier
//) {
//    Box(
//        modifier = modifier
//    ) {
//        TopBrokenWithCircle(
//            backgroundColor = Grass,
//            modifier = Modifier
//                .zIndex(0f)
//        )
//
//        Column {
//            TopAppBar(
//                title = {},
//                navigationIcon = {
//                    IconButton(onClick = { }) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "Voltar",
//                            tint = Color.White
//                        )
//                    }
//                },
//                actions = {
//                    IconButton(onClick = { }) {
//                        Icon(
//                            imageVector = Icons.Default.FavoriteBorder,
//                            contentDescription = "Favorito",
//                            tint = Color.White
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color.Transparent
//                ),
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            BoxWithConstraints(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(0.8f), // mantém a proporção quadrada
//                contentAlignment = Alignment.TopCenter
//            ) {
//                val imageSize = maxWidth * 0.5f // 50% da largura disponível
//                val backgroundImage = maxWidth * 0.7f
//
//                Image(
//                    painter = painterResource(Res.drawable.ic_leaf),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(backgroundImage)
//                        .padding(8.dp),
//                    alpha = 0.2f
//                )
//
////                AppImage(
////                    imageURL = imageUrl,
////                    contentDescription = name,
////                    modifier = Modifier
////                        .align(Alignment.BottomCenter)
////                        .size(imageSize)
////                        .padding(8.dp)
////                )
//            }
//
//            Text(text = name, style = MaterialTheme.typography.titleMedium)
//            Text(text = number, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
//        }
//    }
//}
//
//@Preview
//@Composable
//fun PreviewPokemonCard() {
//    PokedexTheme {
//        PokemonCard(
//            name = "Bulbasaur",
//            number = "N°001",
//            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/1.gif",
//        )
//    }
//}