val ktor_version: String by project
val coroutines_version: String by project
val slf4j_version: String by project
val kotlin_version: String by project

plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.serialization") version "1.8.21"
    application
    `java-library`
    `maven-publish`
    signing
    // id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover") version "0.7.0-Alpha"
}

version = "0.3.0"

/*java {
    withJavadocJar()
    withSourcesJar()
}*/

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")

    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("org.slf4j:slf4j-simple:$slf4j_version")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

// import Kover config here later

// import publishing config here later

/*
tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}*/
