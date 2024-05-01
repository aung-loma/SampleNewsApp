package co.just.simple.myapplication.components.editText

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldSample(
    modifier: Modifier = Modifier,
    value : TextFieldValue = TextFieldValue(),
    hint : String, onValueChanged: (TextFieldValue) -> Unit
) {
    val scope = rememberCoroutineScope()

    OutlinedTextField(
        modifier = modifier.heightIn(min = 32.dp) // Set minimum height
            .padding(vertical = 4.dp), // Reduce vertical padding
        value = value,
        onValueChange = {
            scope.launch {
            onValueChanged(it)
        } },
        label = { Text(hint) },
        textStyle = MaterialTheme.typography.labelLarge,
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleOutlinedTextFieldSample(value = TextFieldValue(), hint = "hint", onValueChanged = {})
}