package com.example.mireakotlincompose

import android.app.Application
import com.example.mireakotlincompose.data.dataModule
import com.example.mireakotlincompose.data.database.di.databaseModule
import com.example.mireakotlincompose.data.network.di.networkModule
import com.example.mireakotlincompose.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                databaseModule(),
                networkModule(),
                dataModule(),
                presentationModule()
            )
        }
    }
}