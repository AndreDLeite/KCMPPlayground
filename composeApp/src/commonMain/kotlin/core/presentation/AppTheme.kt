package core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

enum class AppTheme {
    GoldenYellow, MacRed, BlueySky
}

private val YellowColorScheme = lightColorScheme(
    primary = LightOrange,
    secondary = SandYellow,
    secondaryContainer = StrongDarkYellow,
    tertiary = MidnightBlack,
    tertiaryContainer = MidnightBlack,
    background = DesertWhite,
    onBackground = MidnightBlack,
    surfaceBright = StarYellow,
    surfaceContainerLow = LightWhite,
    onSurface = TransparentLightWhite,
    error = VividRed,
)


private val RedColorScheme = lightColorScheme(
    primary = LightRedBlood,
    secondary = StrongDarkRed,
    secondaryContainer = StrongDarkRed,
    tertiary = MidnightBlack,
    tertiaryContainer = MidnightBlack,
    background = DesertWhite,
    onBackground = MidnightBlack,
    surfaceBright = StarYellow,
    surfaceContainerLow = LightWhite,
    onSurface = TransparentLightWhite,
    error = VividRed,
)


private val BlueColorScheme = lightColorScheme(
    primary = LightBlueySky,
    secondary = StrongDarkBlue,
    secondaryContainer = StrongDarkBlue,
    tertiary = MidnightBlack,
    tertiaryContainer = MidnightBlack,
    background = DesertWhite,
    onBackground = MidnightBlack,
    surfaceBright = StarYellow,
    surfaceContainerLow = LightWhite,
    onSurface = TransparentLightWhite,
    error = VividRed,
)

@Composable
fun MyApplicationTheme(
    appTheme: AppTheme,
    content: @Composable () -> Unit
) {
    val colorScheme = when (appTheme) {
        AppTheme.GoldenYellow -> {
            YellowColorScheme
        }
        AppTheme.MacRed -> {
            RedColorScheme
        }
        AppTheme.BlueySky -> {
            BlueColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme, content = content
    )
}