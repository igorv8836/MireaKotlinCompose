package com.example.mireakotlincompose.data.work_manager

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class WorkManagerFactory(
    private val context: Context
) {
    fun createNotificationWorker() {
        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>().build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }
}