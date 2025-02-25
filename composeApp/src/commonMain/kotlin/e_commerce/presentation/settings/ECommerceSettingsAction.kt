package e_commerce.presentation.settings

import core.domain.enums.AppTheme

sealed interface ECommerceSettingsAction {
    data object OnGoBackClick : ECommerceSettingsAction
    data class OnThemeChange(val theme: AppTheme) : ECommerceSettingsAction
}