package net.sherafatpour.cryptocurrencylist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.sherafatpour.cryptocurrencylist.data.Repository
import net.sherafatpour.cryptocurrencylist.data.models.CryptoModel
import net.sherafatpour.cryptocurrencylist.util.DispatcherProvider
import net.sherafatpour.cryptocurrencylist.util.Resource
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val repository: Repository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    sealed class CryptoStatus {
        class Success(val result: CryptoModel) : CryptoStatus()
        class Failure(val error: String) : CryptoStatus()
        object Loading : CryptoStatus()
        object Empty : CryptoStatus()
    }

    private val _crypto = MutableStateFlow<CryptoStatus>(CryptoStatus.Empty)
    val cryptoStatus: StateFlow<CryptoStatus> = _crypto

    fun getCryptoCurrency(
        vs_currency: String,
        order: String,
        per_page: String,
        page: String,
        sparkline: Boolean
    ) {

        viewModelScope.launch(dispatcherProvider.io) {
            _crypto.value = CryptoStatus.Loading
            when (val response =
                repository.getCryptoList(vs_currency, order, per_page, page, sparkline)) {
                is Resource.Error -> _crypto.value = CryptoStatus.Failure(response.message!!)
                is Resource.Success -> {
                    _crypto.value = CryptoStatus.Success(response.data!!)
                }

            }

        }


    }

}