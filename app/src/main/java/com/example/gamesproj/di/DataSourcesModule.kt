package com.example.gamesproj.di

import com.example.gamesproj.model.dataSource.GameDatSource
import org.koin.dsl.module

/**
 * ViewModelsModule
 *
 * Created by Amany
 */

val dataSourceModule = module {
    single {
        GameDatSource(get())
    }
}