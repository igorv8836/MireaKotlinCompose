package com.example.mireakotlincompose.presentation.facts_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mireakotlincompose.App
import com.example.mireakotlincompose.presentation.viewmodel.CatFactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FactsScreen(
    navHostController: NavHostController,
    viewModel: CatFactViewModel = viewModel(factory = (LocalContext.current.applicationContext as App).appComponent.viewModelFactory())

) {
    val facts by viewModel.facts.collectAsStateWithLifecycle()


    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Список фактов") })
        }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1.0f)
                    .padding(bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(facts) { fact ->
                    FactItem(text = fact.fact)
                }
            }

            Button(
                onClick = { viewModel.getNewFact() }
            ) {
                Text(text = "Получить новый факт")
            }
        }
    }
}

@Composable
fun FactItem(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(16.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 20.sp
        )
    }
}

