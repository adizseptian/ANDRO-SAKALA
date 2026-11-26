package id.sakala.merchant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import id.sakala.merchant.ui.SakalaApp
import id.sakala.merchant.ui.theme.SakalaTheme
import id.sakala.merchant.ui.AppViewModel
import id.sakala.merchant.data.AppDatabase
import id.sakala.merchant.data.SakalaRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getInstance(applicationContext)
        val repository = SakalaRepository(database)
        setContent {
            SakalaTheme {
                val vm: AppViewModel = viewModel(factory = AppViewModel.factory(repository))
                SakalaApp(vm)
            }
        }
    }
}
