package co.just.simple.myapplication.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FillButton( modifier : Modifier = Modifier, name : String = "Click", onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(4.dp))
    ) {
        Text(
            text = name ,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
            )
    }
}