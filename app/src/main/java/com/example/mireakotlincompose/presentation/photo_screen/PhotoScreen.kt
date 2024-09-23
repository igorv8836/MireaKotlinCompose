package com.example.mireakotlincompose.presentation.photo_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mireakotlincompose.presentation.viewmodel.SharedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PhotoScreen() {
    val viewModel: SharedViewModel = koinViewModel()
    var inputUrl by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        AsyncImage(
            model = inputUrl,
            contentDescription = "Image",
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = inputUrl,
            onValueChange = { inputUrl = it },
            label = { Text("Введите URL: ") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
        )
        Text("Васильев Игорь, ИКБО-06-22")
        Button(
            onClick = {
                viewModel.savePhoto(inputUrl)
            },
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
        ) {
            Text("Загрузить")
        }
    }
}