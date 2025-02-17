package core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun DSDefaultText(
    text: String,
    fontSize: TextUnit = MaterialTheme.typography.titleMedium.fontSize,
    fontWeight: FontWeight = FontWeight.SemiBold,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle()
) {
    Text(
        text = text,
        fontWeight = fontWeight,
        maxLines = 1,
        color = MaterialTheme.colorScheme.tertiary,
        fontSize = fontSize,
        overflow = TextOverflow.Ellipsis,
        style = style,
        modifier = modifier,
    )
}