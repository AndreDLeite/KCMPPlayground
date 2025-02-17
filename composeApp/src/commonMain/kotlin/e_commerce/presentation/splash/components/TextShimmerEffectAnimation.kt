package e_commerce.presentation.splash.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.text.TextStyle
import core.presentation.DSDefaultText

@Composable
fun TextShimmerEffectAnimation(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animationProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Restart
        ),
        label = "AnimationProgress"
    )

    val brush = remember(animationProgress) {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                val width = size.width
                val gradientWidth = width * 2
                val gradientProgress = gradientWidth * animationProgress
                return LinearGradientShader(
                    from = Offset(-width + gradientProgress, 0f),
                    to = Offset(gradientProgress, 0f),
                    colors = listOf(
                        color.copy(alpha = 0.2f),
                        color,
                        color.copy(alpha = 0.2f)
                    ),
                )
            }

        }
    }
    DSDefaultText(
        text = text,
        style = TextStyle(
            brush = brush,
        ),
        modifier = modifier
    )
}