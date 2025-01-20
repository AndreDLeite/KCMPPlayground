package core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun DSDefaultText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        maxLines = 1,
        fontSize = MaterialTheme.typography.titleMedium.fontSize,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}