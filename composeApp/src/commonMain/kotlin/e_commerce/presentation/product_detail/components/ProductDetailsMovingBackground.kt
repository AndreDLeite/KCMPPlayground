package e_commerce.presentation.product_detail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import core.presentation.LoadingScreen
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.baseline_shopping_cart_24
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProductDetailsMovingBackground(
    productImage: String,
    onBackClick: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    val infiniteTransition = rememberInfiniteTransition(label = "background")
    val targetOffset = with(LocalDensity.current) {
        1000.dp.toPx()
    }

    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = targetOffset,
        animationSpec = infiniteRepeatable(
            tween(50000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offset"
    )

    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }

    val painter = rememberAsyncImagePainter(
        model = productImage,
        onSuccess = {
            imageLoadResult = Result.success(it.painter)
        },
        onError = {
            it.result.throwable.printStackTrace()
            imageLoadResult = Result.failure(Exception("Error loading image"))
        }
    )
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxWidth()
                    .background(color = Color.Blue)
                    .blur(40.dp)
                    .drawWithCache {
                        val brushSize = 400f
                        val brush = Brush.linearGradient(
                            colors = listOf(Color(0xff720f22), Color(0xffca865b)),
                            start = Offset(offset, offset),
                            end = Offset(offset + brushSize, brushSize),
                            tileMode = TileMode.Mirror
                        )
                        onDrawBehind {
                            drawRect(brush = brush)
                        }
                    }
                    .padding(12.dp)
            )

            Box(
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
            )
        }
        IconButton(
            onClick = {
                onBackClick()
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 16.dp, start = 16.dp)
                .statusBarsPadding()
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .background(Color.White)
                    .padding(12.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxHeight(0.05f)
            )
            AnimatedContent(
                targetState = imageLoadResult
            ) { result ->
                when (result) {
                    null -> LoadingScreen()
                    else -> {
                        Image(
                            painter = if (result.isSuccess) painter else painterResource(Res.drawable.baseline_shopping_cart_24),
                            contentDescription = "product_image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .border(0.5.dp, Color.Black, CircleShape)
                                .clip(CircleShape)

                        )
                    }
                }
            }
            content()
        }
    }
}