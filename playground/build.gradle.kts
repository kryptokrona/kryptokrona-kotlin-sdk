val coroutines_version: String by project
val slf4j_version: String by project

plugins {
    kotlin("jvm") version "1.8.10"
    application
}

group = "org.kryptokrona.sdk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":kryptokrona-core"))
    implementation(project(":kryptokrona-crypto"))
    implementation(project(":kryptokrona-http"))
    implementation(project(":kryptokrona-util"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("org.slf4j:slf4j-simple:$slf4j_version")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}