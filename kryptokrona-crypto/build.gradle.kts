import org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile

val ossrhUsername: String? = System.getProperty("ossrhUsername")
val ossrhPassword: String? = System.getProperty("ossrhPassword") // this file should be in the HOME directory gradle.properties

plugins {
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.dokka")
    `java-library`
    `maven-publish`
    signing
    application
}

group = "org.kryptokrona.sdk"
version = "0.2.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.5.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
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
                description.set("The crypto library for communicating with Kryptokrona nodes")
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
                username = ossrhUsername
                password = ossrhPassword
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

// compile the crypto rust library
tasks.register<Exec>("rustCompile") {
    workingDir = file("${rootDir}/crypto")
    commandLine("cargo", "build", "--release")
}

val rustCryptoDir = "${rootDir}/crypto"
val sharedLibraryPath = when {
    org.gradle.internal.os.OperatingSystem.current().isWindows -> "$rustCryptoDir/target/release/crypto.dll"
    org.gradle.internal.os.OperatingSystem.current().isMacOsX -> "$rustCryptoDir/target/release/libcrypto.dylib"
    else -> "$rustCryptoDir/target/release/libcrypto.so"
}

val copyRustLibrary by tasks.registering(Copy::class) {
    from(sharedLibraryPath)
    into("$buildDir/libs")
}

val copyRustHeader by tasks.registering(Copy::class) {
    from("$rustCryptoDir/target/crypto.h")
    into("$buildDir/headers")
}

tasks.named("build") {
    dependsOn("rustCompile")
    dependsOn(copyRustLibrary)
    dependsOn(copyRustHeader)
}

tasks.withType(KotlinNativeCompile::class.java) {
    dependsOn("rustCompile")
    dependsOn(copyRustLibrary)
    dependsOn(copyRustHeader)
}

// install cbindgen
tasks.register<Exec>("installCbindgen") {
    commandLine("cargo", "install", "cbindgen")
    doFirst {
        val process = ProcessBuilder("cargo", "install", "--list")
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .start()
        process.waitFor()
        val output = process.inputStream.bufferedReader().readText()
        if (output.contains("cbindgen")) {
            logger.lifecycle("cbindgen already installed")
            throw StopExecutionException()
        }
    }
}

// generate the C header files using cbindgen
tasks.register<Exec>("generateCHeaders") {
    workingDir = file("${rootDir}/crypto")
    commandLine("cbindgen", "--config", "cbindgen.toml", "--crate", "crypto", "--output", "target/crypto.h")
    dependsOn("installCbindgen")
}

tasks.named("rustCompile") {
    dependsOn("generateCHeaders")
}