package com.givekesh.places.data.di.module

import com.givekesh.places.data.source.remote.NetworkApi
import com.givekesh.places.data.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideGsonConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .also { it.level = HttpLoggingInterceptor.Level.BODY }


    @Singleton
    @Provides
    fun provideOkhttp(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        factory: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(factory)

    @Singleton
    @Provides
    fun provideNetworkApi(retrofit: Retrofit.Builder): NetworkApi = retrofit.build()
        .create(NetworkApi::class.java)
}