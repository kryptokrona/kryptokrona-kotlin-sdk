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

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}