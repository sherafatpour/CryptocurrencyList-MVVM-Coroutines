package net.sherafatpour.cryptocurrencylist.data

import net.sherafatpour.cryptocurrencylist.data.models.CryptoModel
import net.sherafatpour.cryptocurrencylist.util.Resource
import javax.inject.Inject

class Repository @Inject constructor(private val api: CryptoApi):MainRepository {
    override suspend fun getCryptoList(
        vs_currency: String,
        order: String,
        per_page: String,
        page: String,
        sparkline: Boolean
    ): Resource<CryptoModel> {
        return try {
            val response = api.getCryptoCurrencyList(vs_currency,order,per_page,page,sparkline)
            val result = response.body()
            if (response.isSuccessful && result != null){

                Resource.Success(result)
            }else{
                Resource.Error(response.message())
            }
        }  catch (e:Exception){
            Resource.Error(e.message!!)


        }
    }
}