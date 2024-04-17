package asterixorobelix.cashRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import asterixorobelix.cashRegister.components.CurrencyAmountUI
import asterixorobelix.cashRegister.databinding.FragmentCashRegisterBinding
import asterixorobelix.cashRegister.theme.DarkBlue
import asterixorobelix.cashRegister.theme.LightestBlue
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CashRegisterFragment : Fragment() {

    private val amountViewModel: AmountViewModel by viewModel()
    private var _binding: FragmentCashRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCashRegisterBinding.inflate(inflater, container, false)
        _binding?.composeView?.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                CashRegisterUI()
            }
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Composable
    fun CashRegisterUI() {
        Column {
            CurrencyAmountUI(amountViewModel::saveAmountEntered)
            AmountsUI(amountViewModel.amounts)
        }
    }

    @Composable
    fun AmountsUI(savedAmounts: List<Int>) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = DarkBlue)
                .padding( 20.dp)
        ) {
            if (savedAmounts.isNotEmpty()) {
                AmountGrid(savedAmounts)
            } else {
                Text(
                    text = stringResource(id = R.string.no_entries),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = LightestBlue,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            TotalLine()
            TotalAmount(savedAmounts.sum())
        }
    }

    @Composable
    fun TotalLine() {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightestBlue)
                .height(4.dp)
        )
    }

    @Composable
    fun TotalAmount(totalText: Int) {
        Text(
            text = formatToCurrencyText(totalText),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = LightestBlue,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 10.dp)
        )
    }

    @Composable
    fun AmountGrid(amounts: List<Int>) {
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 400.dp),
            modifier = Modifier
                .verticalScroll(
                    rememberScrollState()
                )
                .fillMaxWidth()
                .size(200.dp),
            content = {
                amounts.let {
                    items(it.size) { amountIndex ->
                        Text(
                            text = formatToCurrencyText(it[amountIndex]),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = LightestBlue,
                            textAlign = TextAlign.End,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

            })
    }
}