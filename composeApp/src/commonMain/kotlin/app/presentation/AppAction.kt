package app.presentation

import core.presentation.AppTheme

sealed interface AppAction {
    data class OnThemeChange(val theme: AppTheme) : AppAction
}
