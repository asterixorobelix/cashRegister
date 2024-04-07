package asterixorobelix.cashRegister.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import asterixorobelix.cashRegister.R
import asterixorobelix.cashRegister.theme.LightestBlue

/**
 * Creates a Currency UI with amount formatting
 * @param onDone method to be executed when the done button is clicked
 *
 */
@Composable
fun CurrencyAmountUI(onDone: (String) -> Unit) {

    var registerAmount by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    TextField(
        textStyle = amountEntryTextStyle,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .onKeyEvent {
                if (it.key == Key.Delete || it.key == Key.Backspace) {
                    registerAmount = TextFieldValue(registerAmount.text.dropLast(1))
                    true
                } else
                    false
            },
        value = registerAmount,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = LightestBlue,
            unfocusedContainerColor = LightestBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.enter_amount_placeholder_text),
                style = amountEntryTextStyle,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        },
        onValueChange = {
            registerAmount = TextFieldValue(it.text.take(MAX_INPUT))
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone(registerAmount.text)
                registerAmount = TextFieldValue()
            }
        ),
        visualTransformation = CurrencyVisualTransformation()
    )
}

private val amountEntryTextStyle =
    TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.End)

//Less than 1 million
private const val MAX_INPUT = 8
