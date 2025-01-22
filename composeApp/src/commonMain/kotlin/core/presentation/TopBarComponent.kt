package core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(
    backgroundColor: Color = LightOrange,
    middleText: String = String(),
    withBackButton: Boolean = true,
    onBackClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = backgroundColor
        ),
        modifier = Modifier
            .background(backgroundColor)
            .padding(8.dp),
        title = {
            Text(
                middleText,
                fontWeight = FontWeight.W500,
                color = Color.Black,
                fontSize = 24.sp
            )
        },
        navigationIcon = {
            if(withBackButton) {
                IconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = {
                        onBackClick.invoke()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .background(LightWhite)
                            .padding(12.dp)
                    )
                }
            }
        },
    )
}