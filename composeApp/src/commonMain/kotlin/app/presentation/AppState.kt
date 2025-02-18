package app.presentation

import core.domain.enums.AppTheme

data class AppState(
    val appTheme: AppTheme = AppTheme.GoldenYellow,
)
