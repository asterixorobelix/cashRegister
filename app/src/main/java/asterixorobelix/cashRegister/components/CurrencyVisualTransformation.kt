package asterixorobelix.cashRegister.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.core.text.isDigitsOnly
import asterixorobelix.cashRegister.formatToCurrencyText

/**
 * Visual Transformation which formats the input to look like currency
 */
class CurrencyVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text.trim()

        return when {
            originalText.isEmpty() -> TransformedText(text, OffsetMapping.Identity)
            !originalText.isDigitsOnly() -> TransformedText(text, OffsetMapping.Identity)
            else -> {
                val formattedText = formatToCurrencyText(originalText)
                TransformedText(
                    text = AnnotatedString(formattedText),
                    offsetMapping = object : OffsetMapping {
                        override fun originalToTransformed(offset: Int): Int {
                            return formattedText.length

                        }

                        override fun transformedToOriginal(offset: Int): Int {
                            return originalText.length
                        }

                    }
                )
            }
        }
    }
}