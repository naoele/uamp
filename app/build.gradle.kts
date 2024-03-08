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
    alias(libs.plugins.android.application)
    alias(libs.plugins.gradle.android.cache.fix)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.example.android.uamp"
    compileSdk = libs.versions.androidCompileSdkVersion.get().toInt()

    defaultConfig {
        applicationId = "com.example.android.uamp.next"
        versionCode = 1
        versionName = "1.0"

        minSdkVersion(libs.versions.androidMinSdkVersion.get())
        targetSdkVersion(libs.versions.androidTargetSdkVersion.get())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
        compose = true
        buildConfig = true
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
}

kapt {
    correctErrorTypes = true
}

dependencies {
    // UAmp module's
    implementation(projects.common)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.recyclerview.selection)

    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    testImplementation(libs.androidx.arch.core.testing)
    testImplementation(libs.androidx.lifecycle.runtime.testing)

    // Glide dependencies
    implementation(libs.glide)
    kapt(libs.glide.compiler)
}
