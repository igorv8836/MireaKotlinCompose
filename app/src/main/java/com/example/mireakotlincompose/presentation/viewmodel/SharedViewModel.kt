package com.example.mireakotlincompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mireakotlincompose.data.image_loader.ImageLoader
import com.example.mireakotlincompose.data.work_manager.WorkManagerFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SharedViewModel(
    private val imageLoader: ImageLoader,
    private val workManagerFactory: WorkManagerFactory
) : ViewModel() {
    fun savePhoto(url: String) {
        viewModelScope.launch {
            try {
                val bitmap = async { imageLoader.loadImageBitmap(url) }
                val fileName = System.currentTimeMillis().toString() + ".jpg"
                bitmap.await()?.let {
                    async { imageLoader.saveImageToStorage(it, fileName) }
                }
            } catch (_: Exception) {

            }
        }
    }

    fun sendNotification() {
        workManagerFactory.createNotificationWorker()
    }
}