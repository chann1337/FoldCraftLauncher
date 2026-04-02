import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.tungsten.fclauncher"
    compileSdk = libs.versions.compileSdk.get().toInt()

        defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        
        // Добавляем поддержку 64-бит для твоего Snapdragon 8 Elite
        ndk {
            abiFilters.add("arm64-v8a")
            abiFilters.add("armeabi-v7a")
        }

        // Подключаем твой Client ID из секретов GitHub
        val msId = System.getenv("MS_CLIENT_ID") ?: "Dfd6ade0-268d-464f-b00e-d19eea7b2ef5"
        buildConfigField("String", "MS_CLIENT_ID", "\"$msId\"")
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    externalNativeBuild {
        ndkBuild {
            path = file("src/main/jni/Android.mk")
        }
    }

    ndkVersion = "27.0.12077973"

        buildFeatures {
        prefab = true
        buildConfig = true
    }


    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}

dependencies {
    implementation(libs.bytehook)
    implementation(libs.appcompat)
    implementation(libs.material)
}
