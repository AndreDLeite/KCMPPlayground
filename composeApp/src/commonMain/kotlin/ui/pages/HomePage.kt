package ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import ui.viewmodel.HomePageViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomePage(navController: NavHostController) {
    val viewModel = koinViewModel<HomePageViewModel>()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {

        Text(
            text = viewModel.message.value.ifEmpty { "Hey there, how's it going?" },
            color = Color.Red,
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = {
            viewModel.callUseCase()
        }) {
            Text(text = "Call use case")
        }

    }
}