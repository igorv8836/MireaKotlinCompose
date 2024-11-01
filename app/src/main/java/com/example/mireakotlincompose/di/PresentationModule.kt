package com.example.mireakotlincompose.di

import com.example.mireakotlincompose.data.image_loader.ImageLoader
import com.example.mireakotlincompose.data.repository.CatFactsRepository
import com.example.mireakotlincompose.data.work_manager.WorkManagerFactory
import com.example.mireakotlincompose.presentation.viewmodel.CatFactViewModel
import com.example.mireakotlincompose.presentation.viewmodel.SharedViewModel
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideCatFactViewModel(repository: CatFactsRepository): CatFactViewModel {
        return CatFactViewModel(repository = repository)
    }

    @Provides
    fun provideSharedViewModel(
        workManagerFactory: WorkManagerFactory,
        imageLoader: ImageLoader
    ): SharedViewModel {
        return SharedViewModel(imageLoader = imageLoader, workManagerFactory = workManagerFactory)
    }
}