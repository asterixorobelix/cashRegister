package asterixorobelix.cashRegister

import com.google.common.truth.Truth
import org.junit.Test

class CurrencyUtilsTests {

    @Test
    fun `confirm null handling`() {
        Truth.assertThat(formatToCurrencyText("hello")).isEqualTo("R0,00")
    }

    @Test
    fun `confirm string to int to formatted string handling`() {
        Truth.assertThat(formatToCurrencyText("234")).isEqualTo("R2,34")
    }

    @Test
    fun `confirm int to formatted string handling`() {
        Truth.assertThat(formatToCurrencyText(234)).isEqualTo("R2,34")
    }
}