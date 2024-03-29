val coroutines_version: String by project
val slf4j_version: String by project

plugins {
    kotlin("jvm") version "1.8.22"
    id("org.jetbrains.kotlinx.kover") version "0.7.3"
    application
}

group = "org.kryptokrona.sdk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":kryptokrona-wallet"))
    implementation(project(":kryptokrona-crypto"))
    implementation(project(":kryptokrona-node"))
    implementation(project(":kryptokrona-util"))
    implementation(project(":kryptokrona-walletapi"))
    implementation(project(":kryptokrona-service"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("org.slf4j:slf4j-simple:$slf4j_version")

    testImplementation(kotlin("test"))
}

tasks.named<CreateStartScripts>("startScripts") {
    mustRunAfter(":kryptokrona-crypto:copyCLibrary")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

koverReport {
    filters {
        excludes {
            classes("*")
        }
    }
}