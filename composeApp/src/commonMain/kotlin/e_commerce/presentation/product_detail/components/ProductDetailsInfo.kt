package e_commerce.presentation.product_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import e_commerce.domain.models.Product
import e_commerce.presentation.product_detail.ProductDetailState
import e_commerce.presentation.product_home.components.ProductRatingComponent
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.product_details_description_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductDetailsInfo(
    state: ProductDetailState,
    onFavoritesClick: (Product) -> Unit,
) {
    val currentProduct = state.currentProduct ?: return
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 50.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    state.currentProduct.name,
                    fontWeight = FontWeight.W600,
                    color = Color.Black,
                    fontSize = 28.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                ProductRatingComponent(rating = currentProduct.rating)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    currentProduct.price,
                    fontWeight = FontWeight.W400,
                    color = Color.DarkGray,
                    fontSize = 22.sp
                )
            }

            ProductFavoritesComponent(
                modifier = Modifier
                    .padding(8.dp)
                    .size(52.dp),
                product = currentProduct,
                isProductFavorite = state.isFavorite,
                onFavoriteIconClick = { product ->
                    onFavoritesClick(product)
                }
            )
        }


        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider()

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            stringResource(Res.string.product_details_description_title),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 16.sp
        )

        Text(
            currentProduct.description,
            fontWeight = FontWeight.W300,
            color = Color.Black,
            fontSize = 22.sp
        )
    }
}