package e_commerce.presentation.product_home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import core.presentation.LoadingScreen
import e_commerce.domain.models.Product
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.baseline_shopping_cart_24
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProductsListComponent(
    products: List<Product>,
    scrollState: LazyGridState = rememberLazyGridState(),
    onProductClick: (productId: String) -> Unit
) {
    LazyVerticalGrid(
        state = scrollState,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(products.size, key = { index -> products[index].id }) { index ->
                val currentProduct = products[index]
                Card(
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            onProductClick(currentProduct.id)
                        }
                ) {
                    Box(contentAlignment = Alignment.TopEnd) {

                        var imageLoadResult by remember {
                            mutableStateOf<Result<Painter>?>(null)
                        }

                        val painter = rememberAsyncImagePainter(
                            model = currentProduct.image,
                            onSuccess = {
                                imageLoadResult = Result.success(it.painter)
                            },
                            onError = {
                                it.result.throwable.printStackTrace()
                                imageLoadResult = Result.failure(Exception("Error loading image"))
                            }
                        )
                        when(val result = imageLoadResult) {
                            null -> LoadingScreen()
                            else -> {
                                Image(
                                    painter = if(result.isSuccess) painter else painterResource(Res.drawable.baseline_shopping_cart_24),
                                    contentDescription = "product_" + currentProduct.name + "_image",
                                )
                            }
                        }

                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 12.dp)
                    ) {
                        Text(
                            text = currentProduct.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Thin,
                            ),
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = currentProduct.price,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        ProductRatingComponent(
                            modifier = Modifier.fillMaxWidth(),
                            rating = currentProduct.rating
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            currentProduct.description,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(color = Color.Black, fontSize = 12.sp),
                        )
                    }
                }
            }
        }
    )
}