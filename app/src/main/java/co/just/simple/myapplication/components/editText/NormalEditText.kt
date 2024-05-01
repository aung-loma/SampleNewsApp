package co.just.simple.myapplication.components.editText

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun NormalEditText(
    modifier: Modifier = Modifier,
    value : TextFieldValue = TextFieldValue(),
    title : String ,
    hint : String ,onValueChanged: (TextFieldValue) -> Unit) {
    var isFocused by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    Box ( modifier = modifier
        .border(width = 1.dp, color = MaterialTheme.colorScheme.primary)
        .fillMaxWidth()){
        Column {
            Text(text = title, style = MaterialTheme.typography.labelMedium)
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
                value = value,
                onValueChange = {
                    scope.launch {
                        onValueChanged(it)
                    }
                },
                placeholder = {
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp)
                    )
                },
                colors =  TextFieldDefaults.colors(
                    unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = MaterialTheme.colorScheme.primary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(25.dp),
                textStyle = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
                singleLine = true,
                maxLines = 1,
            )
        }
    }
}