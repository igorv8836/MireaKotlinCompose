package com.example.mireakotlincompose.presentation

import com.example.mireakotlincompose.data.dataModule
import com.example.mireakotlincompose.presentation.viewmodel.CatFactViewModel
import com.example.mireakotlincompose.presentation.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun presentationModule() = module {
    includes(dataModule())
    viewModel { CatFactViewModel(get()) }
    viewModel { SharedViewModel(get()) }
}