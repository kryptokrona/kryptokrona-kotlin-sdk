plugins {
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.dokka")
    application
}

group = "org.kryptokrona.sdk"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}