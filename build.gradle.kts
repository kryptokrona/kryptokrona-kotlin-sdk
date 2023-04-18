import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.gitlab.arturbosch.detekt.*

val sdkVersion: String by project

plugins {
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.dokka") version "1.8.10"
    id("io.gitlab.arturbosch.detekt").version("1.23.0-RC1")
}

buildscript {
    dependencies {
        classpath("org.jetbrains.dokka:dokka-base:1.8.10")
    }
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
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

tasks.withType<Detekt>().configureEach {
    jvmTarget = "17"
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = "17"
}

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    config.setFrom(files("$projectDir/detekt-config.yml")) // point to your custom config defining rules to run, overwriting default behavior
}

tasks.withType<Detekt>().configureEach {
    reports {
        html.required.set(true) // observe findings in your browser with structure and code snippets
        xml.required.set(true) // checkstyle like format mainly for integrations like Jenkins
        txt.required.set(true) // similar to the console output, contains issue signature to manually edit baseline files
        sarif.required.set(true) // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with GitHub Code Scanning
        md.required.set(true) // simple Markdown format
    }
}

// Dokka configuration
// TODO: does not work ATM, if you can fix it, please do :)
subprojects {
    apply(plugin = "org.jetbrains.dokka")

    tasks.withType<DokkaTask>().configureEach {
        pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
            footerMessage = "(c) 2022-2023 Kryptokrona Developers"
        }
    }
}

tasks.dokkaHtmlMultiModule {
    moduleName.set("Kryptokrona Kotlin SDK")
}

tasks.register<Copy>("copyDokkaHtmlMultiModule") {
    dependsOn("dokkaHtmlMultiModule")
    println("${projectDir}/docs/${sdkVersion}")
    mkdir("${projectDir}/docs/${sdkVersion}")

    from("${buildDir}/dokka/htmlMultiModule")
    into(layout.buildDirectory.dir("${projectDir}/docs/${sdkVersion}"))
    include("**/*.*")
}