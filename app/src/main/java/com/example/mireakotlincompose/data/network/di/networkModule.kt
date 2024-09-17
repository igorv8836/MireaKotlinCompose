package com.example.mireakotlincompose.data.network.di

import com.example.mireakotlincompose.data.network.api.CatFactsApi
import com.example.mireakotlincompose.data.network.getRetrofit
import org.koin.dsl.module
import retrofit2.Retrofit

fun networkModule() = module {
    single { getRetrofit() }
    single { get<Retrofit>().create(CatFactsApi::class.java) }
}