plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.citycyclerentals"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.citycyclerentals"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // AndroidX Core
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Google Play Services
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)

    // UI Components
    implementation(libs.cardview)

    // Image Loading
    implementation(libs.picasso)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}