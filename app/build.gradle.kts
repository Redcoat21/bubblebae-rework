plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.ksp)
    kotlin("plugin.serialization") version "2.1.21"
    id("com.google.protobuf") version "0.9.4"
}

android {
    namespace = "com.dahrenericsson.bubblebaerework"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dahrenericsson.bubblebaerework"
        minSdk = 31
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

// Protocol buffer configuration
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.23.4"
    }

    // Configure the protobuf plugin to find proto files in the correct location
    generatedFilesBaseDir = "$projectDir/src/generated"

    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("java") {
                    option("lite")
                }
                create("kotlin") {
                    option("lite")
                }
            }

            // Add proto source directory
            task.builtins {
                create("java") {
                    option("lite")
                }
                create("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    testImplementation(libs.jetbrains.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Hilt dependencies
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)

    // Supabase dependencies
    implementation(platform(libs.bom))
    implementation(libs.postgrest.kt)
    implementation(libs.auth.kt)
    implementation(libs.realtime.kt)
    implementation(libs.kotlinx.coroutines.android)

    // Camerax dependencies
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.video)
    implementation(libs.androidx.camera.view)

    // Exoplayer dependencies
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.ui.compose)

    // Coil dependencies for image loading
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Timber dependencies for logging
    implementation(libs.timber)

    // Datastore dependencies
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.core)

    // Protocol Buffers dependencies
    implementation(libs.protobuf.kotlin.lite)
    implementation(libs.protobuf.javalite)

    // Ktor dependencies
    implementation(libs.ktor.client.okhttp)

    // Mockk dependencies for testing
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)

    // Konform dependencies
    implementation(libs.konform.jvm)
}

