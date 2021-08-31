import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.navigationTypeSafeArguments)
    id(Plugins.kotlinParcelize)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlin.time.ExperimentalTime"
}

android {
    compileSdkVersion(Configs.compileSdkVersion)
    buildToolsVersion(Configs.buildToolsVersion)

    buildFeatures {
        dataBinding = true
    }

    bundle {
        language {
            enableSplit = false
        }

        density {
            enableSplit = true
        }

        abi {
            enableSplit = true
        }
    }

    defaultConfig {
        applicationId = Configs.applicationId
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }
    val key: String = gradleLocalProperties(rootDir).getProperty("key")

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(ProGuards.retrofit)
            proguardFiles(ProGuards.okhttp)
            proguardFiles(ProGuards.okio)
            proguardFiles(ProGuards.shapeImageView)
            proguardFiles(getDefaultProguardFile(ProGuards.proguardTxt), ProGuards.androidDefault)
            buildConfigField("String", "key", key)
            buildConfigField("boolean", "enableDebugLogging", "true")
            buildConfigField("String", "PACKAGE_NAME", "\"com.example.gamesproj\"")
            buildConfigField("String", "BASE_URL", "\"https://api.rawg.io/api/\"")
        }

        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(ProGuards.retrofit)
            proguardFiles(ProGuards.okhttp)
            proguardFiles(ProGuards.okio)
            proguardFiles(ProGuards.shapeImageView)
            proguardFiles(getDefaultProguardFile(ProGuards.proguardTxt), ProGuards.androidDefault)
            buildConfigField("boolean", "enableDebugLogging", "false")
            buildConfigField("String", "PACKAGE_NAME", "\"com.example.gamesproj\"")
            buildConfigField("String", "BASE_URL", "\"https://api.rawg.io/api/\"")
        }

        packagingOptions {
            exclude("META-INF/DEPENDENCIES")
            exclude("META-INF/LICENSE")
            exclude("META-INF/LICENSE.txt")
            exclude("META-INF/license.txt")
            exclude("META-INF/NOTICE")
            exclude("META-INF/NOTICE.txt")
            exclude("META-INF/notice.txt")
            exclude("META-INF/ASL2.0")
            exclude("META-INF/main.kotlin_module")
            exclude("META-INF/atomicfu.kotlin_module")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


dependencies {
    // Libraries and jars.
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin.
    implementation(Dependencies.Kotlin.stdLib)
    implementation(Dependencies.Kotlin.reflect)

    // Coroutines.
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)

    // Androidx.
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.material)
    implementation(Dependencies.AndroidX.recyclerView)
    implementation(Dependencies.AndroidX.legacySupportV4)
    implementation(Dependencies.AndroidX.legacySupportCoreUi)
    implementation(Dependencies.AndroidX.multiDex)

    // LiveData & ViewModel.
    implementation(Dependencies.Lifecycle.extensions)
    implementation(Dependencies.Lifecycle.viewmodelKtx)
    kapt(Dependencies.Lifecycle.compiler)

    // Navigation.
    // The Navigation Architecture Component.
    // Simplifies the implementation of navigation between destinations in your app.
    implementation(Dependencies.Navigation.fragmentKtx)
    implementation(Dependencies.Navigation.uiKtx)

    // Timber.
    // Logger with a small, extensible API
    // which provides utility on top of Android's normal Log class.
    implementation(Dependencies.timber)

    // Koin.
    // Dependency injector.
    implementation(Dependencies.Koin.androidxViewModel)

    // Retrofit.
    // Type-safe HTTP client.
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.networkResponseAdapter)


    // OkHttp Logging Interceptor.
    // For HTTP logging.
    implementation(Dependencies.Retrofit.loggingInterceptor)
    implementation(Dependencies.Retrofit.json)
    implementation(Dependencies.json)

    // Room.
    // Persistence library provides an abstraction layer over SQLite
    // to allow for more robust database access while harnessing the full power of SQLite.
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)
    kapt(Dependencies.Room.compiler)

    // Coil.
    // An image loading library for Android backed by Kotlin Coroutines.
    implementation(Dependencies.coil)

    // Shape Image View.
    // Custom shaped android image view components.
    implementation(Dependencies.shapeImageView)

    // SDP - a scalable size unit.
    // An android lib that provides a new size unit - sdp (scalable dp).
    // This size unit scales with the screen size.
    // It can help Android developers with supporting multiple screens.
    implementation(Dependencies.sdp)

    // SSP - a scalable size unit for texts.
    // An android lib that provides a new size unit - ssp (scalable sp).
    // This size unit scales with the screen size based on the sp size unit (for texts).
    // It can help Android developers with supporting multiple screens.
    implementation(Dependencies.ssp)

    //pagination
    implementation(Dependencies.pagination)


}