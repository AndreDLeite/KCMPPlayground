package home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import home.presentation.viewmodel.HomePageViewModel

@Composable
fun HomeScreenRoot(
    viewModel: HomePageViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onAction = { action ->
            when (action) {
                HomeScreenAction.OnBackButtonClick -> onBackClick()
                else                               -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun HomeScreen(
    state: HomeScreenState,
    onAction: (HomeScreenAction) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {

        Text(
            text = state.message,
            color = Color.Red,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = "Esta na navgraph do ecommerce",
            color = Color.Red,
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = {
            onAction(HomeScreenAction.OnUseCaseButtonClick)
        }) {
            Text(text = "Call use case")
        }

    }
}