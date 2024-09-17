package com.example.mireakotlincompose.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mireakotlincompose.data.database.model.CatFactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatFactDao {
    @Insert
    suspend fun insert(catFact: CatFactEntity)

    @Query("SELECT * FROM cat_facts")
    fun getAllCatFacts(): Flow<List<CatFactEntity>>
}