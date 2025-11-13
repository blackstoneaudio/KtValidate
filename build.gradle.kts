plugins {
    kotlin("multiplatform") version "2.2.21"
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

    watchosArm32()
    watchosArm64()
    watchosDeviceArm64()
    watchosX64()
    watchosSimulatorArm64()
    iosArm64()
    iosX64()
    iosSimulatorArm64()


    // Create a task to build a fat framework.
//    tasks.register<org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask>("debugFatFramework") {
//        // The fat framework must have the same base name as the initial frameworks.
//        baseName = "KtValidate"
//        // The default destination directory is "<build directory>/fat-framework".
//        destinationDir = buildDir.resolve("KtValidate/debug")
//        // Specify the frameworks to be merged.
//        from(
//            iosX64.binaries.getFramework("DEBUG"),
//            ios64.binaries.getFramework("DEBUG")
//        )
//    }


}

publishing {
    // this fetches our credentials from ~/.gradle/gradle.properties
    val mavenUser: String by project
    val mavenPassword: String by project

    repositories {
        maven {
            setUrl("https://repos.awhb.dev/releases")
            authentication {
                create("basic", BasicAuthentication::class.java)
            }
            credentials {
                username = mavenUser
                password = mavenPassword
            }
        }
    }
}
