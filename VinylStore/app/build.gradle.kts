plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.vinylstore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vinylstore"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Core Android libraries
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.8.0")

    // Compose dependencies
    implementation("androidx.compose.ui:ui:1.6.0")
    implementation("androidx.compose.material:material:1.6.0")
    implementation("androidx.compose.material3:material3:1.2.0-alpha08")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.0")
    implementation("androidx.compose.foundation:foundation:1.6.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.0")

    // Firebase dependencies
    implementation(platform("com.google.firebase:firebase-bom:32.3.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-database-ktx")

    // Test dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.0")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.0")

    // Jetpack Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.3")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.0.0"))
    implementation("com.google.firebase:firebase-firestore-ktx:24.9.1")

    // Core Android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    implementation("androidx.compose.material3:material3:1.2.0-alpha08")
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material:material:1.5.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0")

    implementation("androidx.navigation:navigation-compose:2.6.0-alpha01")
    implementation("com.google.firebase:firebase-firestore:24.5.0")
    implementation("com.google.firebase:firebase-auth:22.1.1")
    implementation("com.google.firebase:firebase-firestore:24.8.0")
}

apply(plugin = "com.google.gms.google-services")
