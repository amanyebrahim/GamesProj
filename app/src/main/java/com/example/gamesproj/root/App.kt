package com.example.gamesproj.root


import androidx.multidex.MultiDexApplication
import com.example.gamesproj.BuildConfig
import com.example.gamesproj.di.apiModule
import com.example.gamesproj.di.modelModule
import com.example.gamesproj.di.viewModelsModule
import kotlinx.coroutines.flow.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import timber.log.Timber


/**
 * App
 */
class App : MultiDexApplication() {
    //region Lifecycle methods
    override fun onCreate() {
        super.onCreate()

        // Initialize Koin.
        initKoin()

        // Initialize libraries.
        initLibraries()
    }
    //endregion

    //region Private methods
    /**
     * Initialize dependency injection using Koin.
     */
    private fun initKoin() {
        startKoin {
            // Use AndroidLogger as Koin Logger.
           // androidLogger(Level.INFO)

            // Use the Android context given there.
            androidContext(this@App)

            // Module list.
            modules(getKoinModules())
        }
    }

    /**
     * Get a list of dependency injection used in application.
     */
    private fun getKoinModules(): List<Module> {
        return listOf(
            apiModule,
            modelModule,
            viewModelsModule
        )
    }

    /**
     * Initialize libraries used in the application.
     */
    private fun initLibraries() {
        initTimber()
    }
    /**
     * Initialize Timber.
     * For "DEBUG" plant a "DebugTree" for logging in Logcat.
     * For "RELEASE" plant a "CrashReportingTree" for logging in Fabric.
     */
    private fun initTimber() {
        when (BuildConfig.enableDebugLogging) {
            true -> Timber.plant(Timber.DebugTree())
        }
    }
    //endregion
}