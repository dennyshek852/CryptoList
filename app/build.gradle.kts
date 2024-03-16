plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}

android {
    namespace = "com.test.cryptolist"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.test.cryptolist"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE-notice.md"
            excludes += "META-INF/LICENSE.md"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.room:room-common:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.test:core-ktx:1.5.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // For test
    val mockk_version = "1.13.10"
    testImplementation("io.mockk:mockk-android:$mockk_version")
    testImplementation("io.mockk:mockk:$mockk_version")

    // JUnit4 Framework
    testImplementation("junit:junit:4.13.2")

    // (Optional) If you're using AndroidX, this Android Test runner for JUnit4 should also be added
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    // Coroutines async task
    val coroutines_version = "1.7.1"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")
    // Coroutines test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")
    // Coroutines android test
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")

    //Room local database
    val room_version = "2.6.1"
    kapt("androidx.room:room-compiler:$room_version")

    // Test helpers
    testImplementation("androidx.room:room-testing:$room_version")


    val koin_version = "3.5.3"
    // Koin
    implementation("io.insert-koin:koin-android:$koin_version")
    implementation("io.insert-koin:koin-androidx-compose:$koin_version")
    androidTestImplementation("io.insert-koin:koin-test-junit4:$koin_version")

    //serialization
    implementation("com.google.code.gson:gson:2.8.8")

    //navigation
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha10")
    implementation("androidx.compose.ui:ui-viewbinding")


}