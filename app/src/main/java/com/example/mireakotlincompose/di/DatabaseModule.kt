package com.example.mireakotlincompose.di

import android.content.Context
import androidx.room.Room
import com.example.mireakotlincompose.data.database.dao.CatFactDao
import com.example.mireakotlincompose.data.database.db.CatFactDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule() {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): CatFactDatabase {
        return Room.databaseBuilder(
            context,
            CatFactDatabase::class.java,
            "cat_fact_database"
        ).build()
    }

    @Provides
    fun provideCatFactDao(database: CatFactDatabase): CatFactDao {
        return database.catFactDao()
    }
}