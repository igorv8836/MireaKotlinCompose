package com.example.mireakotlincompose.presentation.list_screen

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun ListScreen(navHostController: NavHostController) {
    val context = LocalContext.current

    val photos = context.getExternalFilesDir(null)?.listFiles()
        ?.filter { it.extension == "jpg" }?.sortedByDescending { it.lastModified() } ?: emptyList()
    Log.i("test", photos.toString())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentPadding = PaddingValues(8.dp),
        content = {
            items(photos.size) { index ->
                val photoFile = photos[index]
                PhotoItem(photoUri = photoFile.toUri())
            }
        }
    )
}

@Composable
fun PhotoItem(photoUri: Uri) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        AsyncImage(
            model = photoUri,
            contentDescription = "Photo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}