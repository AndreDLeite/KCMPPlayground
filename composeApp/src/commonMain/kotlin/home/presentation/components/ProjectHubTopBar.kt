package home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import core.presentation.LightOrange
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.baseline_clear_24
import kmpplayground.composeapp.generated.resources.baseline_search_24
import kmpplayground.composeapp.generated.resources.welcome_projects_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectHubTopBar(
    isSearching: Boolean,
    onSearchClick: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LightOrange,
        ),
        title = {
            Text(
                text = stringResource(Res.string.welcome_projects_title),
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
            )
        },
        actions = {
            val painter = if (isSearching) {
                painterResource(Res.drawable.baseline_clear_24)
            } else {
                painterResource(Res.drawable.baseline_search_24)
            }
            Icon(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(28.dp)
                    .clickable {
                        onSearchClick()
                    }
            )
        }
    )
}