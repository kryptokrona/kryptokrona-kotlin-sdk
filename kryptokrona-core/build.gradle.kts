plugins {
    kotlin("jvm") version "1.8.10"
    application
    `java-library`
    `maven-publish`
    signing
}

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
    jvmToolchain(8)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "kryptokrona-http"
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
                description.set("A HTTP library for communicating with Kryptokrona nodes")
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
            // change URLs to point to your repos, e.g. http://my.org/repo
            val releasesRepoUrl = uri(layout.buildDirectory.dir("repos/releases"))
            val snapshotsRepoUrl = uri(layout.buildDirectory.dir("repos/snapshots"))
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
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