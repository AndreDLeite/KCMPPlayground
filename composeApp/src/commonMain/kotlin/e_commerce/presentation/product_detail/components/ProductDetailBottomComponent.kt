package e_commerce.presentation.product_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.presentation.DSDefaultText
import e_commerce.domain.models.Product
import e_commerce.presentation.product_detail.ProductDetailAction
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.add_to_cart
import kmpplayground.composeapp.generated.resources.baseline_remove_24
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductDetailsBottomComponent(
    quantity: Int = 0,
    currentProduct: Product,
    onAction: (ProductDetailAction) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 20.dp)
            .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
            .background(color = Color.White)
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(0.7.dp, Color.Black, CircleShape)
                .clip(shape = RoundedCornerShape(50.dp))
                .background(color = MaterialTheme.colorScheme.onBackground)
        ) {

            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = {
                    onAction(ProductDetailAction.OnMinusClick)
                }
            ) {
                Icon(
                    //Icons.Filled.Remove is missing (???)
                    painter = painterResource(Res.drawable.baseline_remove_24),
                    contentDescription = "remove ${currentProduct.name} from cart quantity",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(12.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            DSDefaultText(
                text = quantity.toString(),
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = {
                    onAction(ProductDetailAction.OnPlusClick)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add ${currentProduct.name} from cart quantity",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            enabled = quantity != 0,
            onClick = {
                onAction(
                    ProductDetailAction.OnAddToCartClick(
                        product = currentProduct,
                        quantity = quantity
                    )
                )
            }
        ) {
            Text(
                stringResource(Res.string.add_to_cart),
                color = Color.Black,
            )
        }

    }
}