val ktor_version: String by project
val coroutines_version: String by project
val slf4j_version: String by project
val kotlin_version: String by project
val ossrhUsername: String? = System.getProperty("ossrhUsername")
val ossrhPassword: String? = System.getProperty("ossrhPassword") // this file should be in the HOME directory gradle.properties

plugins {
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
    id("org.jetbrains.dokka")
    `java-library`
    `maven-publish`
    signing
    application
}

version = "0.2.0-SNAPSHOT"

java {
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":kryptokrona-util"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")

    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("org.slf4j:slf4j-simple:$slf4j_version")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "kryptokrona-http"
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
                name.set("Kryptokrona HTTP")
                description.set("The HTTP library for communicating with Kryptokrona nodes")
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