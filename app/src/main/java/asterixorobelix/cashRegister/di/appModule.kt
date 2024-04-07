package asterixorobelix.cashRegister.di

import asterixorobelix.cashRegister.AmountViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AmountViewModel() }
}