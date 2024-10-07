package com.example.mireakotlincompose.presentation.list_screen

import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


data class PhotoWithMetadata(val uri: Uri, val date: Long)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var photos by remember { mutableStateOf<List<PhotoWithMetadata>>(emptyList()) }

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            val loadedPhotos = context.getExternalFilesDir(null)?.listFiles()
                ?.filter { it.extension == "jpg" }
                ?.map { PhotoWithMetadata(it.toUri(), it.lastModified()) }
                ?.sortedByDescending { it.date } ?: emptyList()

            photos = loadedPhotos
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Список") })
        }) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize().padding(it)
                .padding(8.dp),
            contentPadding = PaddingValues(8.dp),
            content = {
                items(photos.size) { index ->
                    PhotoItem(photoUri = photos[index])
                }
            }
        )
    }
}

@Composable
fun PhotoItem(photoUri: PhotoWithMetadata) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(photoUri.uri)
        .crossfade(true)
        .diskCachePolicy(coil.request.CachePolicy.ENABLED)
        .memoryCachePolicy(coil.request.CachePolicy.ENABLED)
        .build()
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    val formattedDate = dateFormat.format(Date(photoUri.date))
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = "Photo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = formattedDate,
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp)
        )
    }
}
