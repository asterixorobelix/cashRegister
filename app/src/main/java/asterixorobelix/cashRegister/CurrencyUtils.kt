package asterixorobelix.cashRegister

import java.text.NumberFormat
import java.util.Locale

private val numberFormatter =
    NumberFormat.getCurrencyInstance(Locale("en", "ZA"))

/**
 * Formats text to currency
 * @param unformattedString eg "213" will be returned as "R2.13"
 */
fun formatToCurrencyText(unformattedString: String): String {
    return numberFormatter.format((unformattedString.toDoubleOrNull() ?: 0.0) / 100)
}

/**
 * Formats text to currency
 * @param unformattedString eg 213 will be returned as "R2.13"
 */
fun formatToCurrencyText(unformattedString: Int): String {
    return numberFormatter.format(unformattedString.toDouble() / 100)
}