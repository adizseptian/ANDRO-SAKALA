package id.sakala.merchant.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import id.sakala.merchant.data.ProductEntity
import id.sakala.merchant.data.SakalaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewModel(private val repository: SakalaRepository) : ViewModel() {
    val products = repository.products.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val events = repository.events.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun addProduct(name: String, unit: String, pack: String, price: String, onResult: (String?) -> Unit) {
        viewModelScope.launch {
            runCatching {
                repository.addProduct(name, unit, pack.toLong(), price.toLong())
            }.onSuccess { onResult(null) }
             .onFailure { onResult(it.message ?: "Tidak dapat menyimpan produk.") }
        }
    }

    fun recordExpense(amount: String, funding: String, note: String, onResult: (String?) -> Unit) {
        viewModelScope.launch {
            runCatching { repository.recordExpense(amount.toLong(), funding, note) }
                .onSuccess { onResult(null) }
                .onFailure { onResult(it.message ?: "Tidak dapat menyimpan pengeluaran.") }
        }
    }

    fun recordDeposit(amount: String, note: String, onResult: (String?) -> Unit) {
        viewModelScope.launch {
            runCatching { repository.recordDeposit(amount.toLong(), note) }
                .onSuccess { onResult(null) }
                .onFailure { onResult(it.message ?: "Tidak dapat menyimpan setoran.") }
        }
    }

    companion object {
        fun factory(repository: SakalaRepository) = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AppViewModel(repository) as T
            }
        }
    }
}
