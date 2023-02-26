import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.dorongold.task-tree") version "2.1.0"
    kotlin("jvm") version "1.7.10"
    application
}

group "org.kryptokrona.sdk"

// getting project properties (gradle.properties)
val sdkVersion: String by project

repositories {
    mavenCentral()
}

sourceSets {
    main {
        resources {
            srcDirs ("src/main/resources")
        }
    }
}

dependencies {
    // implementation
    implementation("io.github.classgraph:classgraph:4.8.149")
    implementation("com.github.seancfoley:ipaddress:5.3.4")
    implementation("com.fasterxml.jackson:jackson-bom:2.13.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4.1")
    implementation("org.slf4j:slf4j-api:2.0.0")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.1.3")
	implementation("org.apache.httpcomponents.client5:httpclient5-fluent:5.1.3")
    implementation("org.slf4j:slf4j-simple:2.0.0")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.18.0")

    // test
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
    testCompileOnly("org.projectlombok:lombok:1.18.24")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")

    // various
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
}

tasks.withType<Test> {
    useJUnitPlatform()
    failFast = true
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}