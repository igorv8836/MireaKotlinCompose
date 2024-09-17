package com.example.mireakotlincompose.data.repository

import com.example.mireakotlincompose.data.database.dao.CatFactDao
import com.example.mireakotlincompose.data.database.model.CatFactEntity
import com.example.mireakotlincompose.data.network.api.CatFactsApi

class CatFactsRepository(
    private val catFactsApi: CatFactsApi,
    private val catFactDao: CatFactDao
) {
    suspend fun getCatFact() {
        val fact = catFactsApi.getCatFact()
        catFactDao.insert(
            CatFactEntity(
                fact = fact.fact
            )
        )
    }

    fun getFacts() = catFactDao.getAllCatFacts()
}