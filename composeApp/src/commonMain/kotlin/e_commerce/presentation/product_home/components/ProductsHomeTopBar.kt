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
import core.presentation.UiText
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.baseline_favorite_24
import kmpplayground.composeapp.generated.resources.go_back
import kmpplayground.composeapp.generated.resources.welcome_user
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    userName: String,
    onFavoritesClick: () -> Unit,
    onShoppingCartClick: () -> Unit,
    onProfileIconClick: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LightOrange,
        ),
        title = {
            Text(
                text = stringResource(Res.string.welcome_user, userName),
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
                        onProfileIconClick()
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