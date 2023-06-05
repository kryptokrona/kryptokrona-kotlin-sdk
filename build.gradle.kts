import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.report.ReportMergeTask
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val sdkVersion: String by project

plugins {
    kotlin("jvm") version "1.8.21"
    id("org.jetbrains.dokka") version "1.8.10"
    id("io.gitlab.arturbosch.detekt").version("1.23.0")
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

buildscript {
    dependencies {
        classpath("org.jetbrains.dokka:dokka-base:1.8.20")
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

// Dokka configuration
// TODO: does not work ATM, if you can fix it, please do :)
subprojects {
    // exclude the "playground" module from the dokka task
    if (name != "playground") {
        apply(plugin = "org.jetbrains.dokka")

        tasks.withType<DokkaTask>().configureEach {
            pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
                footerMessage = "(c) 2022-2023 Kryptokrona Developers"
            }
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

val detektReportMergeSarif by tasks.registering(ReportMergeTask::class) {
    output.set(layout.buildDirectory.file("reports/detekt/merge.sarif"))
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        buildUponDefaultConfig = true
        config.setFrom(file("${rootDir}/config/detekt.yml"))
    }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = "17"
        reports {
            xml.required.set(true)
            html.required.set(true)
            txt.required.set(true)
            sarif.required.set(true)
            md.required.set(true)
        }
        basePath = rootDir.absolutePath
        finalizedBy(detektReportMergeSarif)
    }
    detektReportMergeSarif {
        input.from(tasks.withType<Detekt>().map { it.sarifReportFile })
    }
    tasks.withType<DetektCreateBaselineTask>().configureEach {
        jvmTarget = "17"
    }
}
