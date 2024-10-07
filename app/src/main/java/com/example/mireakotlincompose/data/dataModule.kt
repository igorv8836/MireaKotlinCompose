package com.example.mireakotlincompose.data

import com.example.mireakotlincompose.data.database.di.databaseModule
import com.example.mireakotlincompose.data.image_loader.ImageLoader
import com.example.mireakotlincompose.data.network.di.networkModule
import com.example.mireakotlincompose.data.repository.CatFactsRepository
import com.example.mireakotlincompose.data.work_manager.WorkManagerFactory
import org.koin.dsl.module

fun dataModule() = module {
    includes(databaseModule(), networkModule())
    single { CatFactsRepository(get(), get()) }
    factory { ImageLoader(get()) }
    factory { WorkManagerFactory(get()) }
}