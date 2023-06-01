val ktor_version: String by project

plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.serialization") version "1.8.21"
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover") version "0.7.1"
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
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

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
            artifactId = "kryptokrona-util"
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
                name.set("Kryptokrona Util")
                description.set("The Kryptokrona util library.")
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