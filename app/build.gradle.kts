plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

    id("androidx.navigation.safeargs.kotlin")  // for kotlin-only modules
}

android {
    namespace = "com.example.flow_retrofit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.flow_retrofit"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.google.dagger:hilt-android:2.47") // latest 2.49
    kapt("com.google.dagger:hilt-android-compiler:2.47")  // latest 2.48

    // lifecycle
    val lifecycle_version = "2.8.0"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    // for retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")   // new 2.11.0
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // for room
    val room_version = "2.6.1"
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")

    // for coroutine
    val coroutine_version = "1.8.0"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version")

    // for paging 3
    val paging_version = "3.3.0"
    implementation("androidx.paging:paging-runtime-ktx:$paging_version")

    implementation("com.diogobernardino:williamchart:3.10.1")

    implementation("org.jsoup:jsoup:1.17.2")


    implementation("io.coil-kt:coil:2.6.0")

    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))

    // define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // for navigation
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}