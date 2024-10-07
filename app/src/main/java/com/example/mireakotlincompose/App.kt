package com.example.mireakotlincompose

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mireakotlincompose.data.dataModule
import com.example.mireakotlincompose.data.database.di.databaseModule
import com.example.mireakotlincompose.data.network.di.networkModule
import com.example.mireakotlincompose.data.work_manager.NotificationWorker
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

        scheduleNotificationWork()

    }

    private fun scheduleNotificationWork() {
        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "NotificationWork",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}