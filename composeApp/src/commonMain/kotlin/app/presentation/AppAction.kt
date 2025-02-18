package app.presentation

import core.domain.enums.AppTheme

sealed interface AppAction {
    data class OnThemeChange(val theme: AppTheme) : AppAction
}
