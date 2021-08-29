package com.example.gamesproj.di

import com.example.gamesproj.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * ViewModelsModule
 *
 * Created by Amany
 */

val viewModelsModule = module {
    viewModel {
        HomeViewModel(get())
    }
}