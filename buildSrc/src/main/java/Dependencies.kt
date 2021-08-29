object Dependencies {
    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.AndroidX.fragmentKtx}"

        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"

        const val material = "com.google.android.material:material:${Versions.AndroidX.material}"

        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerView}"

        const val legacySupportV4 =
            "androidx.legacy:legacy-support-v4:${Versions.AndroidX.legacySupportV4}"

        const val legacySupportCoreUi =
            "androidx.legacy:legacy-support-core-ui:${Versions.AndroidX.legacySupportCoreUi}"

        const val multiDex = "androidx.multidex:multidex:${Versions.AndroidX.multiDex}"

        const val dataStore =
            "androidx.datastore:datastore-preferences:${Versions.AndroidX.dataStore}"
    }

    object Lifecycle {
        const val extensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.Lifecycle.extensions}"

        const val viewmodelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Lifecycle.viewModelKtx}"

        const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.Lifecycle.compiler}"
    }

    object Navigation {
        const val fragmentKtx =
            "android.arch.navigation:navigation-fragment-ktx:${Versions.Arc.navigation}"

        const val uiKtx = "android.arch.navigation:navigation-ui-ktx:${Versions.Arc.navigation}"
    }

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    object Koin {
        const val androidxViewModel = "org.koin:koin-androidx-viewmodel:${Versions.
        koin}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

        const val networkResponseAdapter =
            "com.github.haroldadmin:NetworkResponseAdapter:${Versions.networkResponseAdapter}"

        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    }

    object Moshi {
        const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val adapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
        const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.AndroidX.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.AndroidX.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.AndroidX.room}"
    }

    const val coil = "io.coil-kt:coil:${Versions.coil}"

    const val shapeImageView =
        "com.github.siyamed:android-shape-imageview:${Versions.shapeImageView}"


    const val sdp = "com.intuit.sdp:sdp-android:${Versions.sdp}"
    const val ssp = "com.intuit.ssp:ssp-android:${Versions.ssp}"

    const val pagination="androidx.paging:paging-runtime:${Versions.Arc.pagination}"

}