plugins {
    kotlin("multiplatform") version "1.9.0"
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
    watchosSimulatorArm64()
    watchos()
    iosArm64()
    iosX64()
    iosSimulatorArm64()

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
