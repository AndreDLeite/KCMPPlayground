package e_commerce.presentation.product_detail.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import e_commerce.domain.models.Product

@Composable
fun ProductFavoritesComponent(
    product: Product,
    isProductFavorite: Boolean,
    modifier: Modifier,
    onFavoriteIconClick: (Product) -> Unit,
) {
    Surface(
        shape = CircleShape,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier,
    ) {
        IconToggleButton(
            checked = isProductFavorite,
            onCheckedChange = {
                onFavoriteIconClick(product)
            }
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.error,
                imageVector = if (isProductFavorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = null
            )
        }
    }
}