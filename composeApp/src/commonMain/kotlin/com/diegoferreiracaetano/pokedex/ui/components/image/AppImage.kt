package com.diegoferreiracaetano.pokedex.ui.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.placeholder

@Composable
fun AppImage(
    imageURL: String,
    contentDescription: String? = null,
    placeholder: DrawableResource = Res.drawable.placeholder,
    modifier: Modifier = Modifier
) {
    val isPreview = LocalInspectionMode.current

    Image(
        painter = if(isPreview) painterResource(placeholder) else rememberImagePainter(imageURL),
        contentDescription = contentDescription,
        modifier = modifier.size(120.dp)
    )
}