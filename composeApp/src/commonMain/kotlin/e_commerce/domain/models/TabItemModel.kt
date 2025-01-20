package e_commerce.domain.models

import androidx.compose.ui.graphics.vector.ImageVector
import e_commerce.domain.enums.TabType

data class TabItemModel(
    val title: String,
    val unSelectedItem: ImageVector,
    val selectedIcon: ImageVector,
    val type: TabType
)
