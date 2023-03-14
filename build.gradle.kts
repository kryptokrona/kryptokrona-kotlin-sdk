import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.dokka.base.DokkaBaseConfiguration

val sdkVersion: String by project

plugins {
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.dokka") version "1.8.10"
}

buildscript {
    dependencies {
        classpath("org.jetbrains.dokka:dokka-base:1.8.10")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    // failFast = true
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<DokkaTask>().configureEach {
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        footerMessage = "(c) 2022-2023 Kryptokrona Developers"
    }
}

tasks.register<Copy>("copyDokkaHtmlMultiModule") {
    dependsOn("dokkaHtmlMultiModule")
    println("${projectDir}/docs/${sdkVersion}")
    mkdir("${projectDir}/docs/${sdkVersion}")

    from("${buildDir}/dokka/htmlMultiModule")
    into(layout.buildDirectory.dir("${projectDir}/docs/${sdkVersion}"))
    include("**/*.*")
}