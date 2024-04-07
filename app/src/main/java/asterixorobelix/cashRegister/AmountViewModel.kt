package asterixorobelix.cashRegister

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlin.math.absoluteValue

class AmountViewModel : ViewModel() {

    private val _amounts = mutableStateListOf<Int>()
    val amounts: List<Int> = _amounts.asReversed()

    /**
     * Save the amount entered by the user
     * @param userEnteredAmount amount to be saved
     */
    fun saveAmountEntered(userEnteredAmount: String) {
        userEnteredAmount.toIntOrNull()?.let {
            if (it > 0)
                _amounts.add(it.absoluteValue)
        }
    }
}