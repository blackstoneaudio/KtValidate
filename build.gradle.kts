plugins {
    kotlin("multiplatform") version "1.5.0"
//    id("com.android.library")
//    id("kotlin-android-extensions")
    id("maven-publish")
}

group = "com.blackstone"
version = "0.1.0"

repositories {
    google()
    mavenCentral()

//    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
}

kotlin {
    jvm()
    val ios64 = iosArm64("ios64")
    val iosX64 = iosX64("iosX64")
    configure(listOf(ios64, iosX64)) {
        binaries.framework {
            baseName = "KtValidate"
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
//        val androidMain by getting
//        val androidTest by getting
//        val iosMain by getting
//        val iosTest by getting
        val jvmMain by getting
    }


    // Create a task to build a fat framework.
    tasks.register<org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask>("debugFatFramework") {
        // The fat framework must have the same base name as the initial frameworks.
        baseName = "KtValidate"
        // The default destination directory is "<build directory>/fat-framework".
        destinationDir = buildDir.resolve("KtValidate/debug")
        // Specify the frameworks to be merged.
        from(
            iosX64.binaries.getFramework("DEBUG"),
            ios64.binaries.getFramework("DEBUG")
        )
    }


}

//android {
//    compileSdkVersion(29)
//    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
//    defaultConfig {
//        minSdkVersion(16)
//        targetSdkVersion(29)
//    }
//}