package net.sherafatpour.cryptocurrencylist.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.sherafatpour.cryptocurrencylist.data.Repository
import net.sherafatpour.cryptocurrencylist.data.models.CryptoModel
import net.sherafatpour.cryptocurrencylist.util.DispatcherProvider
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
  private val  repository: Repository,
  private val dispatcherProvider: DispatcherProvider
):ViewModel() {
    var list: MutableLiveData<CryptoModel> = MutableLiveData<CryptoModel>()

    fun getCryptoCurrency(vs_currency: String,
                                order: String,
                                per_page: String,
                                page: String,
                                sparkline: Boolean){

        viewModelScope.launch(dispatcherProvider.io) {

            val listResponse = repository.getCryptoList(vs_currency, order, per_page, page, sparkline)
            list.postValue(listResponse.data!!)

        }


    }

}