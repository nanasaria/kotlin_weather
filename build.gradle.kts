plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android) apply false
}

android {
    namespace = "com.example.weatherviewapi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.weatherviewapi"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation ("org.java-websocket:Java-WebSocket:1.5.2")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    implementation(libs.annotation.jvm)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}