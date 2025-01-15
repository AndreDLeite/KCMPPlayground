package e_commerce.domain

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItemsModel(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)

