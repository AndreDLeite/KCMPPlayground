package core.presentation.desing_system

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DsAnimatedCounter(
    counter: Int,
    textSize: Dp = 20.dp
) {
    AnimatedContent(
        targetState = counter,
        transitionSpec = {
            if (targetState > initialState) {
                slideInVertically { -it } togetherWith  slideOutVertically { it }
            } else {
                slideInVertically { it } togetherWith slideOutVertically { -it }
            }
        }
    ) { quantity ->
        DSDefaultText(
            text = quantity.toString(),
            modifier = Modifier.size(textSize)
        )
    }
}