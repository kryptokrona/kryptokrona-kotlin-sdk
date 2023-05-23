val ossrhUsername: String? = System.getProperty("ossrhUsername")
val ossrhPassword: String? = System.getProperty("ossrhPassword")

plugins {
    kotlin("jvm") version "1.8.21"
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
