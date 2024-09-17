package com.example.mireakotlincompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mireakotlincompose.data.database.model.CatFactEntity
import com.example.mireakotlincompose.data.repository.CatFactsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CatFactViewModel(private val repository: CatFactsRepository) : ViewModel() {
    val facts: StateFlow<List<CatFactEntity>> = repository.getFacts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun getNewFact() {
        viewModelScope.launch {
            repository.getCatFact()
        }
    }
}