package com.rodrigotristany.globallogic.internal.di.modules

import com.rodrigotristany.globallogic.data.api.GlobalLogicApi
import com.rodrigotristany.globallogic.data.repositories.LaptopRepository
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule{
    companion object {
        private const val BASE_URL: String = "http://private-f0eea-mobilegllatam.apiary-mock.com/"
    }

    @Provides
    @Singleton
    fun provideClient() : OkHttpClient {
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                var request : Request = chain.request()
                val url : HttpUrl = request .url().newBuilder().build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGlobalLogicApi() : GlobalLogicApi {
        return provideRetrofit(BASE_URL, provideClient()).create(GlobalLogicApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLaptopRepository() : LaptopRepository {
        return LaptopRepository(provideGlobalLogicApi())
    }
}
