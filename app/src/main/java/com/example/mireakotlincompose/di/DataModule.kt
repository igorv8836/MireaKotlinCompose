package com.example.mireakotlincompose.di

import android.content.Context
import com.example.mireakotlincompose.data.database.db.CatFactDatabase
import com.example.mireakotlincompose.data.image_loader.ImageLoader
import com.example.mireakotlincompose.data.network.api.CatFactsApi
import com.example.mireakotlincompose.data.repository.CatFactsRepository
import com.example.mireakotlincompose.data.repository.CatFactsRepositoryImpl
import com.example.mireakotlincompose.data.work_manager.WorkManagerFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideCatFactsRepository(
        database: CatFactDatabase,
        api: CatFactsApi
    ): CatFactsRepository {
        return CatFactsRepositoryImpl(catFactDao = database.catFactDao(), catFactsApi = api)
    }

    @Provides
    fun provideImageLoader(context: Context): ImageLoader {
        return ImageLoader(context)
    }

    @Provides
    fun provideWorkManagerFactory(context: Context): WorkManagerFactory {
        return WorkManagerFactory(context)
    }
}