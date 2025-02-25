package app.data.mappers

import core.domain.enums.AppTheme

fun String.toAppTheme(): AppTheme {
    return when (this) {
        AppTheme.GoldenYellow.name -> {
            AppTheme.GoldenYellow
        }

        AppTheme.MacRed.name       -> {
            AppTheme.MacRed
        }

        AppTheme.BlueySky.name     -> {
            AppTheme.BlueySky
        }

        else                       -> {
            AppTheme.GoldenYellow
        }
    }
}
