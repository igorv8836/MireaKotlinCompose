package com.example.mireakotlincompose.data.network.api

import com.example.mireakotlincompose.data.network.model.CatFact
import retrofit2.http.GET

interface CatFactsApi {
    @GET("fact")
    suspend fun getCatFact(): CatFact
}