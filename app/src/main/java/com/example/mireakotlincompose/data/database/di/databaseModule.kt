package com.example.mireakotlincompose.data.database.di

import androidx.room.Room
import com.example.mireakotlincompose.data.database.db.CatFactDatabase
import org.koin.dsl.module

fun databaseModule() = module {
    single {
        Room.databaseBuilder(
            context = get(),
            CatFactDatabase::class.java,
            "cat_fact_database"
        ).build()
    }
    single { get<CatFactDatabase>().catFactDao() }
}