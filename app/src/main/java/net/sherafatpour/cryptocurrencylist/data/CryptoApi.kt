package net.sherafatpour.cryptocurrencylist.data

import net.sherafatpour.cryptocurrencylist.data.models.CryptoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {

    @GET("markets")
    suspend fun getCryptoCurrencyList(
        @Query("vs_currency") vs_currency: String,
        @Query("order") order: String,
        @Query("per_page") per_page: String,
        @Query("page") page: String,
        @Query("sparkline") sparkline: Boolean
    ): Response<CryptoModel>
}