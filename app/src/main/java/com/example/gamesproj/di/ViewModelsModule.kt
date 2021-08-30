package com.example.gamesproj.di

import com.example.gamesproj.ui.gameDetails.GameDetailsViewModel
import com.example.gamesproj.ui.games.GameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * ViewModelsModule
 *
 * Created by Amany
 */

val viewModelsModule = module {
    viewModel {
        GameViewModel(get())
    }

    viewModel {
        GameDetailsViewModel(get())
    }
}