plugins {
    kotlin("multiplatform") version "1.8.10"
    id("maven-publish")
}

group = "com.blackstone"
version = "0.1.0"

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvm()

    js(IR) {
        browser { binaries.executable() }
        nodejs { binaries.executable() }
    }
    val iosArm32 = iosArm32()
    val ios64 = iosArm64()
    val iosX64 = iosX64()
    val iosSimArm64 = iosSimulatorArm64()
    configure(listOf(iosArm32, ios64, iosX64, iosSimArm64)) {
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

publishing {
    // this fetches our credentials from ~/.gradle/gradle.properties
    val mavenUser: String by project
    val mavenPassword: String by project

//    repositories {
//        maven {
//            setUrl("https://repos.awhb.dev/releases")
//            authentication {
//                create("basic", BasicAuthentication::class.java)
//            }
//            credentials {
//                username = mavenUser
//                password = mavenPassword
//            }
//        }
//    }
}