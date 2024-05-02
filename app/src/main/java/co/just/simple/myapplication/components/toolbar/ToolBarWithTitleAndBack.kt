package co.just.simple.myapplication.components.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import co.just.simple.myapplication.ui.theme.IconResource

@Composable
fun ToolBarWithTitleAndBack(
    title : String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Box(modifier = Modifier
                .size(48.dp)
                .clickable {
                    onBackClick.invoke()
                }, contentAlignment = Alignment.Center ) {
                Image(
                    painterResource(id = IconResource.Back),
                    contentDescription =  "Back"
                )
            }

            Text(
                text = title,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp),
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        onEditClick.invoke()
                    },contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = IconResource.Edit), // Use the appropriate icon resource for edit
                    contentDescription = "Edit",
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.2f),
                        Color.Transparent,
                    )
                )
            )
        )
    }
}