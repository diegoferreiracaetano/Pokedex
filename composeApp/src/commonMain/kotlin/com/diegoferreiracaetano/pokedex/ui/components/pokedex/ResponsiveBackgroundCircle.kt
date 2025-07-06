package com.diegoferreiracaetano.pokedex.ui.components.pokedex

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.diegoferreiracaetano.pokedex.ui.theme.Grass
import org.jetbrains.compose.resources.painterResource

import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.ic_leaf

/**
 * Desenha um fundo com uma curva suave na parte inferior, que se adapta a qualquer largura de tela.
 *
 * @param backgroundColor A cor base para o gradiente do fundo.
 * @param height A altura total da área do fundo. O desenho da curva ocorrerá dentro deste espaço.
 * @param modifier O modificador a ser aplicado ao componente.
 */
@Composable
fun ResponsiveBackgroundCircle(
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    height: Dp = 300.dp // Altura fixa e previsível para a área do cabeçalho
) {
    Box(modifier = modifier) {
        val colorGradient = Brush.horizontalGradient(
            colors = listOf(
                backgroundColor,
                backgroundColor.copy(alpha = 0.6f)
            )
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(height) // Usa a altura definida, tornando-a consistente
        ) {
            // 'size' nos dá a largura e altura exatas do Canvas no momento do desenho.
            // Todos os cálculos agora são baseados neste 'size'.

            val width = size.width
            val canvasHeight = size.height

            // 1. Calculamos um raio que seja proporcional à largura.
            // Um fator maior que 1.0 (ex: 1.5f) cria uma curva mais suave e menos acentuada.
            val radius = width * 0.7f

            // 2. O centro do círculo é posicionado horizontalmente no meio.
            val centerX = width / 2f

            // 3. O centro vertical é calculado para que a parte de BAIXO do círculo
            // crie a curva desejada no topo do Canvas.
            // Colocamos o centro BEM ACIMA do canvas.
            val centerY = canvasHeight - radius

            // 4. Desenhamos o círculo. Como o centro está muito acima e o raio é grande,
            // apenas o "arco" inferior do círculo será visível dentro dos limites do Canvas,
            // criando o efeito de curva que você quer.
            drawCircle(
                brush = colorGradient,
                radius = radius,
                center = Offset(x = centerX, y = centerY)
            )
        }

        Image(
            painter = painterResource(Res.drawable.ic_leaf),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(height * 0.8f)
        )
    }
}

@Preview
@Composable
fun ResponsiveBackgroundCirclePreview() {
    ResponsiveBackgroundCircle(backgroundColor = Grass)
}

