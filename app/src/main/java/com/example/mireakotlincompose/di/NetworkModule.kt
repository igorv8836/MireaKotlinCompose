package com.example.mireakotlincompose.di

import com.example.mireakotlincompose.data.network.api.CatFactsApi
import com.example.mireakotlincompose.data.network.getRetrofit
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return getRetrofit()
    }

    @Provides
    fun provideCatFactsApi(retrofit: Retrofit): CatFactsApi {
        return retrofit.create(CatFactsApi::class.java)
    }
}