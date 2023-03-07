val ktor_version: String by project
val coroutines_version: String by project
val slf4j_version: String by project

plugins {
    kotlin("jvm") version "1.8.10"
    application
}

group = "org.kryptokrona"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":kryptokrona-core"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("org.slf4j:slf4j-simple:$slf4j_version")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}