package e_commerce.domain.factory

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ShoppingCart
import e_commerce.domain.enums.TabType
import e_commerce.domain.models.TabItemModel

object TabItemFactory {
    fun generateTabs() = listOf(
        TabItemModel(
            title = "Products",
            unSelectedItem = Icons.Outlined.ShoppingCart,
            selectedIcon = Icons.Filled.ShoppingCart,
            type = TabType.PRODUCTS_LIST,
        ),
        TabItemModel(
            title = "Map",
            unSelectedItem = Icons.Outlined.LocationOn,
            selectedIcon = Icons.Filled.LocationOn,
            type = TabType.MAPS,
        ),
    )
}