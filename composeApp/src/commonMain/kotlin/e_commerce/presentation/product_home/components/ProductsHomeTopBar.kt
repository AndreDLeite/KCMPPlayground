package e_commerce.presentation.product_home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import core.presentation.LightOrange
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.baseline_favorite_24
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier,
    onFavoritesClick: () -> Unit,
    onShoppingCartClick: () -> Unit,
    onProfileIconClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LightOrange,
        ),
        title = {
            Text(
                "Welcome, Andre Leite",
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.Menu,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .size(36.dp)
                    .clickable {
                        onProfileIconClick.invoke()
                    },
            )
        },
        actions = {
            Icon(
                painter = painterResource(Res.drawable.baseline_favorite_24),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(28.dp)
                    .clickable {
                        onFavoritesClick()
                    }
            )
            Icon(
                imageVector = Icons.Rounded.ShoppingCart,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .size(28.dp)
                    .clickable {
                        onShoppingCartClick()
                    },
            )
        }
    )
}