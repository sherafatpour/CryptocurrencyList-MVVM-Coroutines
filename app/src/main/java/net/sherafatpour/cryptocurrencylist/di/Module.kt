package net.sherafatpour.cryptocurrencylist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import net.sherafatpour.cryptocurrencylist.data.CryptoApi
import net.sherafatpour.cryptocurrencylist.util.Constant
import net.sherafatpour.cryptocurrencylist.util.DispatcherProvider
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


    @Provides
    @Singleton
    fun provideDispatchers():DispatcherProvider=object :DispatcherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }

}