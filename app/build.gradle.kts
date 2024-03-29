plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.5.31"


    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")


}

android {
    namespace = "com.example.mediaappniklas2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mediaappniklas2"
        minSdk = 24
        targetSdk = 33
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
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"
        }
    }


}

dependencies {
    implementation ("org.slf4j:slf4j-api:1.7.32")
    implementation ("org.slf4j:slf4j-simple:1.7.32")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.annotation:annotation:1.6.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.compose.material:material-icons-extended:1.4.3")
    implementation("io.coil-kt:coil-compose:2.0.0-rc01")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("androidx.navigation:navigation-compose:2.7.4")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.annotation:annotation:1.6.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.compose.material:material-icons-extended:1.4.3")
    implementation("io.coil-kt:coil-compose:2.0.0-rc01")

    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    implementation("androidx.navigation:navigation-compose:2.7.4")
    implementation("com.google.firebase:firebase-firestore-ktx:24.10.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation ("com.squareup.okhttp3:mockwebserver:4.9.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    testImplementation("junit:junit:4.13.2")


    dependencies {
        // Import the Firebase BoM
        implementation(platform("com.google.firebase:firebase-bom:32.7.0"))


        // TODO: Add the dependencies for Firebase products you want to use
        // When using the BoM, don't specify versions in Firebase dependencies
        implementation("com.google.firebase:firebase-analytics")


        // Add the dependencies for any other desired Firebase products
        // https://firebase.google.com/docs/android/setup#available-libraries
        implementation("com.google.firebase:firebase-analytics:21.5.0")
        implementation("com.google.firebase:firebase-firestore-ktx:24.10.0")

    }




}