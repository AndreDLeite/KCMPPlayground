package e_commerce.presentation.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.presentation.AppState
import app.presentation.MainViewModel
import core.domain.enums.AppTheme
import core.presentation.desing_system.DSDefaultText
import core.presentation.desing_system.TopBarComponent
import core.presentation.theme.LightBlueySky
import core.presentation.theme.LightOrange
import core.presentation.theme.LightRedBlood
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.settings_theme_picker_description
import kmpplayground.composeapp.generated.resources.settings_theme_picker_title
import kmpplayground.composeapp.generated.resources.settings_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun ECommerceSettingsScreenRoot(
    viewModel: MainViewModel,
    onGoBack: () -> Unit,
    onThemeChange: (AppTheme) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ECommerceSettingsScreen(
        state = state
    ) { action ->
        when (action) {
            is ECommerceSettingsAction.OnGoBackClick -> onGoBack()
            is ECommerceSettingsAction.OnThemeChange -> onThemeChange(action.theme)
        }
    }
}

@Composable
fun ECommerceSettingsScreen(
    state: AppState,
    onAction: (ECommerceSettingsAction) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarComponent(
                middleText = stringResource(Res.string.settings_title),
            ) {
                onAction(ECommerceSettingsAction.OnGoBackClick)
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp + innerPadding.calculateTopPadding())
        ) {
            DSDefaultText(
                text = stringResource(Res.string.settings_theme_picker_title),
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            DSDefaultText(
                text = stringResource(Res.string.settings_theme_picker_description),
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(25.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(AppTheme.entries.size) { index ->
                    val currentTheme = AppTheme.entries[index]
                    val isSelectedTheme = currentTheme.name == state.appTheme.name

                    val color = when (currentTheme) {
                        AppTheme.MacRed       -> LightRedBlood
                        AppTheme.BlueySky     -> LightBlueySky
                        AppTheme.GoldenYellow -> LightOrange
                    }


                    AnimatedVisibility(visible = isSelectedTheme) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(color)
                                .clickable {
                                    onAction(ECommerceSettingsAction.OnThemeChange(currentTheme))
                                }
                                .then(
                                    if (isSelectedTheme) {
                                        Modifier.border(
                                            2.dp,
                                            MaterialTheme.colorScheme.tertiary,
                                            CircleShape
                                        )
                                    } else {
                                        Modifier
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isSelectedTheme) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    tint = MaterialTheme.colorScheme.tertiary,
                                    contentDescription = null
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}