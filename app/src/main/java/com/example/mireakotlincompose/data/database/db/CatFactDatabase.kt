package com.example.mireakotlincompose.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mireakotlincompose.data.database.dao.CatFactDao
import com.example.mireakotlincompose.data.database.model.CatFactEntity

@Database(entities = [CatFactEntity::class], version = 1)
abstract class CatFactDatabase : RoomDatabase() {
    abstract fun catFactDao(): CatFactDao
}