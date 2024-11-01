package com.example.mireakotlincompose.data.repository

import com.example.mireakotlincompose.data.database.dao.CatFactDao
import com.example.mireakotlincompose.data.database.model.CatFactEntity
import com.example.mireakotlincompose.data.network.api.CatFactsApi

class CatFactsRepositoryImpl(
    private val catFactsApi: CatFactsApi,
    private val catFactDao: CatFactDao
) : CatFactsRepository {
    override suspend fun getCatFact() {
        val fact = catFactsApi.getCatFact()
        catFactDao.insert(
            CatFactEntity(
                fact = fact.fact
            )
        )
    }

    override fun getFacts() = catFactDao.getAllCatFacts()
}