plugins {
    kotlin("multiplatform") version "2.1.21"
    id("maven-publish")
}

group = "com.blackstone"
version = "0.2.0"

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

    val iosArm64Target = iosArm64()
    val iosX64Target = iosX64()
    val iosSimulatorArm64Target = iosSimulatorArm64()

    configure(listOf(iosArm64Target, iosX64Target, iosSimulatorArm64Target)) {
        binaries.framework {
            baseName = "KtValidate"
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting
    }
}

// Create a task to build a fat framework that aggregates simulator/device outputs.
val kotlinExtension =
    extensions.getByType<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension>()

tasks.register<org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask>("debugFatFramework") {
    baseName = "KtValidate"
    destinationDir = buildDir.resolve("KtValidate/debug")
    from(
        kotlinExtension.targets.getByName<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>("iosArm64")
            .binaries.getFramework("DEBUG"),
        kotlinExtension.targets.getByName<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>("iosX64")
            .binaries.getFramework("DEBUG"),
        kotlinExtension.targets.getByName<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>("iosSimulatorArm64")
            .binaries.getFramework("DEBUG")
    )
}

publishing {
    // this fetches our credentials from ~/.gradle/gradle.properties
    val mavenUser: String? by project
    val mavenPassword: String? by project

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
