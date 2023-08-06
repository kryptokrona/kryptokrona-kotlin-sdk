import org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile

val coroutines_version: String by project
val slf4j_version: String by project

plugins {
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.serialization") version "1.8.22"
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover") version "0.7.3"
    `java-library`
    `maven-publish`
    signing
    application
}

group = "org.kryptokrona.sdk"
version = "0.3.0"

repositories {
    mavenCentral()
}

java {
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    implementation(project(":kryptokrona-util"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")

    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("org.slf4j:slf4j-simple:$slf4j_version")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    mustRunAfter("copyCLibrary")
}

kotlin {
    jvmToolchain(17)
}

koverReport {
    filters {
        excludes {
            classes("org.kryptokrona.sdk.crypto.hugin.*")
            classes("org.kryptokrona.sdk.crypto.model.*")
            classes("org.kryptokrona.sdk.crypto.exception.*")
            classes("org.kryptokrona.sdk.crypto.mnemonics.WordList")
        }
    }

    defaults {
        verify {
            onCheck = true
            rule {
                isEnabled = true
                entity = kotlinx.kover.gradle.plugin.dsl.GroupingEntityType.APPLICATION

                filters {
                    excludes {
                        classes("org.kryptokrona.sdk.crypto.hugin.*")
                        classes("org.kryptokrona.sdk.crypto.model.*")
                        classes("org.kryptokrona.sdk.crypto.exception.*")
                        classes("org.kryptokrona.sdk.crypto.mnemonics.WordList")
                    }
                }

                bound {
                    minValue = 40 //TODO change to 60 later
                    maxValue = 90
                    metric = kotlinx.kover.gradle.plugin.dsl.MetricType.LINE
                    aggregation = kotlinx.kover.gradle.plugin.dsl.AggregationType.COVERED_PERCENTAGE
                }
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "kryptokrona-crypto"
            groupId = "org.kryptokrona.sdk"
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("Kryptokrona Crypto")
                description.set("The Kryptokrona crypto library.")
                url.set("https://kryptokrona.org")
                licenses {
                    license {
                        name.set("The 3-Clause BSD License")
                        url.set("https://github.com/kryptokrona/kryptokrona-kotlin-sdk/blob/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("mjovanc")
                        name.set("Marcus Cvjeticanin")
                        email.set("mjovanc@icloud.com")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:kryptokrona/kryptokrona-kotlin-sdk.git")
                    developerConnection.set("scm:git@github.com:kryptokrona/kryptokrona-kotlin-sdk.git")
                    url.set("https://github.com/kryptokrona/kryptokrona-kotlin-sdk")
                }
            }
        }
    }
    repositories {
        maven {
            name = "Sonatype"
            val host = "https://s01.oss.sonatype.org"
            val path = if (version.toString().endsWith("SNAPSHOT")) "/content/repositories/snapshots/"
            else "/service/local/staging/deploy/maven2/"
            url = uri(host.plus(path))
            println("> publish.url: $url")
            println("> publish.path: $path")
            println("> publish.version: $version")

            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                username = System.getenv("ORG_GRADLE_PROJECT_ossrhUsername")
                password = System.getenv("ORG_GRADLE_PROJECT_ossrhPassword")
            }
        }

        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/kryptokrona/kryptokrona-kotlin-sdk")

            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

signing {
    val signingKey = System.getenv("ORG_GRADLE_PROJECT_signingKey")
    val signingPassword = System.getenv("ORG_GRADLE_PROJECT_signingPassword")
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

// compile the crypto C library
val cryptoDir = "${rootDir}/crypto"
val cryptoSharedLibraryPath = when {
    org.gradle.internal.os.OperatingSystem.current().isWindows -> "$cryptoDir/build/crypto.dll"
    org.gradle.internal.os.OperatingSystem.current().isMacOsX -> "$cryptoDir/build/libcrypto.dylib"
    else -> "$cryptoDir/build/libcrypto.so"
}

val ed25519SharedLibraryPath = when {
    org.gradle.internal.os.OperatingSystem.current().isWindows -> "$cryptoDir/build/ed25519.dll"
    org.gradle.internal.os.OperatingSystem.current().isMacOsX -> "$cryptoDir/build/libed25519.dylib"
    else -> "$cryptoDir/build/libed25519.so"
}

tasks.register<Exec>("cCompile") {
    workingDir = file("$cryptoDir")
    commandLine("make", "-B")
}

tasks.register<Exec>("cReCompile") {
    dependsOn("cClean")
    workingDir = file("$cryptoDir")
    commandLine("make", "-B")
}

tasks.register<Exec>("cClean") {
    workingDir = file("$cryptoDir")
    commandLine("rm", "-rf", "$cryptoDir/build")
    commandLine("make", "clean")

    // remove .o files in the cryptoDir and its subdirectories
    println("Removing .o files in $cryptoDir")
    val cryptoDir = file("$cryptoDir")
    cryptoDir.walkTopDown().forEach { file ->
        if (file.extension == "o") {
            file.delete()
        }
    }

    workingDir = file("$cryptoDir/ed25519")
    commandLine("make", "clean")
}

val copyCLibrary by tasks.registering(Copy::class) {
    from(cryptoSharedLibraryPath)
    into("$buildDir/libs")

    from(ed25519SharedLibraryPath)
    into("$buildDir/libs")
}

tasks.named<CreateStartScripts>("startScripts") {
    mustRunAfter("copyCLibrary")
}

tasks.named<Test>("test") {
    mustRunAfter("copyCLibrary")
}

val copyCHeaders by tasks.registering(Copy::class) {
    from("$cryptoDir") {
        include("**/*.h") // copy all files with .h extension
    }
    into("$buildDir/headers")

    from("$cryptoDir/ed25519") {
        include("ed25519/*.h") // copy all files with .h extension
    }
    into("$buildDir/headers/ed25519")
}

tasks.named("assemble") {
    dependsOn("cReCompile")
    dependsOn(copyCLibrary)
    dependsOn(copyCHeaders)
}

tasks.named("clean") {
    dependsOn("cClean")
}

tasks.withType(KotlinNativeCompile::class.java) {
    dependsOn("cCompile")
    dependsOn(copyCLibrary)
    dependsOn(copyCHeaders)
}

val runCLibraryLoader by tasks.registering(JavaExec::class) {
    dependsOn("build")
    classpath = sourceSets.getByName("main").runtimeClasspath
    mainClass.set("org.kryptokrona.sdk.crypto.CLibraryLoader")
    environment(
        if (org.gradle.internal.os.OperatingSystem.current().isMacOsX) "DYLD_LIBRARY_PATH"
        else "LD_LIBRARY_PATH", "$buildDir/libs"
    )
}

