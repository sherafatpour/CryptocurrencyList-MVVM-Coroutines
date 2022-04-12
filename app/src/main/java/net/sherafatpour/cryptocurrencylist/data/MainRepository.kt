package net.sherafatpour.cryptocurrencylist.data

import net.sherafatpour.cryptocurrencylist.data.models.CryptoModel
import net.sherafatpour.cryptocurrencylist.util.Resource

interface MainRepository {

    suspend fun getCryptoList(vs_currency:String
                              ,order:String
                              ,per_page:String
                              ,page:String
                              ,sparkline:Boolean): Resource<CryptoModel>
}