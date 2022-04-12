package net.sherafatpour.cryptocurrencylist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.sherafatpour.cryptocurrencylist.data.CryptoApi
import net.sherafatpour.cryptocurrencylist.util.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class Module {


    @Provides
    @Singleton
    fun provideClient():OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60 * 60, TimeUnit.SECONDS)///60*30
        .readTimeout(60 * 60, TimeUnit.SECONDS)
        .writeTimeout(60 * 60, TimeUnit.SECONDS).build()

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient):CryptoApi = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build().create(CryptoApi::class.java)



}