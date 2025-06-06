package com.diegoferreiracaetano.pokedex.ui.components.loading


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.theme.PokedexTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun AppLoading(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    strokeWidth: Float = 20f
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pokeball_rotation")

    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing)
        ),
        label = "rotation"
    )

    Canvas(modifier = modifier.size(60.dp)) {
        drawArc(
            color = color,
            startAngle = angle,
            sweepAngle = 270f,
            useCenter = false,
            size = Size(size.width, size.height),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
    }
}

@Composable
fun PokeballLoading(modifier: Modifier = Modifier, size: Dp = 64.dp) {
    val rotation by rememberInfiniteTransition(label = "rotate").animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        ),
        label = "rotate_anim"
    )

    Canvas(modifier = modifier.size(size)) {
        val canvasWidth = size.toPx()
        val canvasHeight = size.toPx()
        val radius = canvasWidth / 2
        val center = Offset(x = canvasWidth / 2, y = canvasHeight / 2)

        rotate(degrees = rotation, pivot = center) {
            // Top (Red)
            drawArc(
                color = Color.Red,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(0f, 0f),
                size = Size(canvasWidth, canvasHeight)
            )

            // Bottom (White)
            drawArc(
                color = Color.White,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(0f, 0f),
                size = Size(canvasWidth, canvasHeight)
            )

            // Middle black line
            drawLine(
                color = Color.Black,
                strokeWidth = 8f,
                start = Offset(0f, center.y),
                end = Offset(canvasWidth, center.y)
            )

            // Center circle (black outer)
            drawCircle(
                color = Color.Black,
                radius = radius * 0.25f,
                center = center
            )

            // Center circle (white inner)
            drawCircle(
                color = Color.White,
                radius = radius * 0.15f,
                center = center
            )
        }
    }
}


@Preview
@Composable
fun PokeballLoadingIndicatorPreview() {
    PokedexTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {

            AppLoading()

            PokeballLoading()
        }
    }
}
