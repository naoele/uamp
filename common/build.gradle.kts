/*
 * Copyright 2017 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.gradle.android.cache.fix)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.example.android.uamp"
    compileSdk = libs.versions.androidCompileSdkVersion.get().toInt()

    defaultConfig {

        minSdkVersion(libs.versions.androidMinSdkVersion.get())
        targetSdkVersion(libs.versions.androidTargetSdkVersion.get())

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
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}

dependencies {
    val coroutinesBom = platform(libs.kotlinx.coroutines.bom)
    api(coroutinesBom)
    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutines.android)
    api(libs.kotlinx.coroutines.guava)

    api(libs.androidx.media)

    api(libs.gson)

    // ExoPlayer dependencies
    api(libs.androidx.media3.exoplayer)
    api(libs.androidx.media3.ui)
    api(libs.androidx.media3.common)

    // Glide dependencies
    api(libs.glide)
    kapt(libs.glide.compiler)

    // Testing
    testImplementation(libs.junit4)
    testImplementation(libs.robolectric)
}
