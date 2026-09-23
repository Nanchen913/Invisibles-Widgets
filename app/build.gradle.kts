plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.invisibles.widgets"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.invisibles.widgets"
        minSdk = 26
        targetSdk = 36
        versionCode = 3
        versionName = "1.0.2"
    }

    signingConfigs {
        val signingStorePath = System.getenv("ANDROID_KEYSTORE_PATH")
        val signingPassword = System.getenv("ANDROID_KEYSTORE_PASSWORD")
        if (!signingStorePath.isNullOrBlank() && !signingPassword.isNullOrBlank()) {
            create("release") {
                storeFile = file(signingStorePath)
                storePassword = signingPassword
                keyAlias = "invisibles-widgets"
                keyPassword = signingPassword
                storeType = "pkcs12"
            }
        }
    }

    buildTypes {
        release {
            signingConfigs.findByName("release")?.let { signingConfig = it }
            isMinifyEnabled = true
            isShrinkResources = true
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

    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
}
