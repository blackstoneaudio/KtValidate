plugins {
    kotlin("multiplatform") version "1.4.20-RC"
    id("com.android.library")
    id("kotlin-android-extensions")
}

group = "com.blackstone"
version = "0.1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
}

kotlin {
    android()
    iosX64("ios") {
        binaries {
            framework {
                baseName = "library"
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting
        val androidTest by getting
        val iosMain by getting
        val iosTest by getting
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(29)
    }
}