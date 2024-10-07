package com.example.mireakotlincompose.presentation.photo_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mireakotlincompose.presentation.viewmodel.SharedViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoScreen() {
    val viewModel: SharedViewModel = koinViewModel()
    var inputUrl by remember { mutableStateOf("") }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Экран 1", modifier = Modifier.padding(vertical = 8.dp))
                    Text("Экран 2", modifier = Modifier.padding(vertical = 8.dp))
                    Text("Экран 3", modifier = Modifier.padding(vertical = 8.dp))
                    Text("Экран 4", modifier = Modifier.padding(vertical = 8.dp))
                    Text("Экран 5", modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        },
    ){
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Фото") },
                    colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Меню")
                        }
                    })
            }) {
            Column(
                modifier = Modifier.padding(it).padding(16.dp)
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )
                Text("Васильев Игорь, ИКБО-06-22")
                Button(
                    onClick = {
                        viewModel.savePhoto(inputUrl)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Text("Загрузить")
                }
            }
        }
    }

}