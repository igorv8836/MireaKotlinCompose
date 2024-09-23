package com.example.mireakotlincompose.data.image_loader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class ImageLoader(
    private val context: Context
) {

    suspend fun loadImageBitmap(url: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            val loader = context.imageLoader
            val request = ImageRequest.Builder(context)
                .data(url)
                .allowHardware(false)
                .build()
            val result = (loader.execute(request) as? SuccessResult)?.drawable
            (result as? BitmapDrawable)?.bitmap
        }
    }

    fun saveImageToStorage(bitmap: Bitmap, fileName: String) {
        val picturesDir = context.getExternalFilesDir(null)
        val file = File(picturesDir, fileName)
        var outputStream: OutputStream? = null

        try {
            outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
        } catch (_: Exception) {
        } finally {
            outputStream?.close()
        }
    }
}