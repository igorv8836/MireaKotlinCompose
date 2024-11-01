package com.example.mireakotlincompose.data.repository

import com.example.mireakotlincompose.data.database.model.CatFactEntity
import kotlinx.coroutines.flow.Flow

interface CatFactsRepository {
    suspend fun getCatFact()
    fun getFacts(): Flow<List<CatFactEntity>>
}