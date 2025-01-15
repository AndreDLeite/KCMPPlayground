package e_commerce.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.presentation.LightOrange
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.baseline_shopping_cart_24
import kmpplayground.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun ECommerceSplashScreenRoot() {
    ECommerceSplashScreen()
}


@Composable
fun ECommerceSplashScreen(
    modifier: Modifier = Modifier

) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(Res.drawable.baseline_shopping_cart_24),
            contentDescription = "your e-commerce logo",
            colorFilter = ColorFilter.tint(LightOrange),
            modifier = modifier.size(64.dp)
        )
        Spacer(modifier = modifier.height(24.dp))
        Text(
            "Welcome to Your E-Commerce App",
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        )
    }
}