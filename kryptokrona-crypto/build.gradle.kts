import org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile

val coroutines_version: String by project
val ossrhUsername: String? = System.getProperty("ossrhUsername")
val ossrhPassword: String? = System.getProperty("ossrhPassword") // this file should be in the HOME directory gradle.properties

plugins {
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover") version "0.7.0-Alpha"
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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")

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

// compile the crypto C library
val cryptoDir = "${rootDir}/crypto"
val sharedLibraryPath = when {
    org.gradle.internal.os.OperatingSystem.current().isWindows -> "$cryptoDir/build/crypto.dll"
    org.gradle.internal.os.OperatingSystem.current().isMacOsX -> "$cryptoDir/build/libcrypto.dylib"
    else -> "$cryptoDir/build/libcrypto.so"
}

tasks.register<Exec>("cCompile") {
    workingDir = file("$cryptoDir")
    commandLine("make")
}

tasks.register<Exec>("cClean") {
    workingDir = file("$cryptoDir")
    commandLine("make", "clean")
}

val copyCLibrary by tasks.registering(Copy::class) {
    from(sharedLibraryPath)
    into("$buildDir/libs")
}

tasks.named<CreateStartScripts>("startScripts") {
    mustRunAfter("copyCLibrary")
}

val copyCHeaders by tasks.registering(Copy::class) {
    from("$cryptoDir") {
        include("**/*.h") // copy all files with .h extension
    }
    into("$buildDir/headers")
}

tasks.named("build") {
    dependsOn("cCompile")
    dependsOn(copyCLibrary)
    dependsOn(copyCHeaders)
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

