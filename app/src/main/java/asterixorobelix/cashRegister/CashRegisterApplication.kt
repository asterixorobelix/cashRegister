package asterixorobelix.cashRegister

import android.app.Application
import asterixorobelix.cashRegister.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class CashRegisterApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@CashRegisterApplication)
            modules(appModule)
        }
    }
}