package com.example.gamesproj.di


import com.example.gamesproj.model.ModelRepository
import org.koin.dsl.module

/**
 * ModelModule
 */
val modelModule = module {
    // Provide instance of [ModelRepo].
    single {
        ModelRepository(get())
    }
}