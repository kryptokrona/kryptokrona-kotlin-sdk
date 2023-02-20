import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

//---------------------------------------------------------------------------------
// PLUGINS
//---------------------------------------------------------------------------------

plugins {
    id("java")
    id("com.dorongold.task-tree") version "2.1.0"
    //TODO: temporarily disable
    // id("checkstyle")
    // id("pmd")
    // id("de.aaschmid.cpd") version "3.3"
    // id("com.github.spotbugs") version "5.0.4"
    // id("jacoco") // if this does not work, remove the id around the name
    kotlin("jvm") version "1.7.10"
    id("org.springframework.boot") version "2.5.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("c")
    application
}

group "org.kryptokrona.sdk"

// getting project properties (gradle.properties)
val sdkVersion: String by project

repositories {
    mavenCentral()
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

sourceSets {
    main {
        resources {
            srcDirs ("src/main/resources")
        }
    }
}

//---------------------------------------------------------------------------------
// DEPENDENCIES
//---------------------------------------------------------------------------------

dependencies {
    // implementation
    implementation("io.github.classgraph:classgraph:4.8.149")
    implementation("com.github.seancfoley:ipaddress:5.3.4")
    implementation("com.fasterxml.jackson:jackson-bom:2.13.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4.1")
    implementation("org.slf4j:slf4j-api:2.0.0")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.1.3")
	implementation("org.apache.httpcomponents.client5:httpclient5-fluent:5.1.3")
    implementation("io.reactivex.rxjava3:rxjava:3.1.4")
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


//---------------------------------------------------------------------------------
// JAVA NATIVE INTERFACE (JNI)
//---------------------------------------------------------------------------------

apply("model.gradle")

//---------------------------------------------------------------------------------
// TEST CONFIGURATION
//---------------------------------------------------------------------------------

tasks.withType<Test> {
    useJUnitPlatform()
    failFast = true // stop early to avoid running whole test suit if one fails
    // finalizedBy("jacocoTestReport") // report is always generated after tests run
}

/*tasks.jacocoTestReport {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.isEnabled = true
        html.destination = file("$buildDir/kryptokrona-sdk-report")
    }

    finalizedBy("jacocoTestCoverageVerification")
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                //TODO this is set to 0.0 since we do not have implemented proper
                // unit tests yet to cover all code. Set to 0.7 when done.
                minimum = "0.0".toBigDecimal()
            }
        }
    }
}*/

//---------------------------------------------------------------------------------
// STATIC CODE ANALYSIS CONFIGURATION
//---------------------------------------------------------------------------------

/*checkstyle {
    config = rootProject.resources.text.fromFile("${projectDir}/gradle/static-code-analysis/checkstyle/checkstyle.xml")
    toolVersion = "8.12"
    isIgnoreFailures = false
}

pmd {
    toolVersion = "6.7.0"
    isIgnoreFailures = false
    ruleSetFiles = files("${projectDir}/gradle/static-code-analysis/pmd/ruleset.xml")
    ruleSets = listOf()
    rulesMinimumPriority.set(3)
}

cpd {
    language = "java"
    toolVersion = "6.1.0"
    minimumTokenCount = 200 // approximately 5-10 lines
}

tasks.named<de.aaschmid.gradle.plugins.cpd.Cpd>("cpdCheck") {
    ignoreFailures = true
    minimumTokenCount = 25
    setSource(files(
        // only check java source code
        subprojects.flatMap { it.the<SourceSetContainer>()["main"].java.srcDirs },
        subprojects.flatMap { it.the<SourceSetContainer>()["test"].java.srcDirs }
    ))
}

tasks.spotbugsMain {
    reports.create("html") {
        required.set(true)
        outputLocation.set(file("$projectDir/build/reports/spotbugs/main/spotbugs.html"))
        setStylesheet("fancy-hist.xsl")
    }
    excludeFilter.set(rootProject.file("${projectDir}/gradle/static-code-analysis/spotbugs/spotbugs-exclude.xml"))
    reports.maybeCreate("xml").isEnabled = false
    reports.maybeCreate("html").isEnabled = true
    maxHeapSize.set("256m")
}*/


//---------------------------------------------------------------------------------
// TASKS
//---------------------------------------------------------------------------------

tasks.register("printVersion") {
    doLast {
        println(project.version)
    }
}

tasks.register<Copy>("copyJavaDoc") {
    dependsOn("javadoc")
    println("${projectDir}/docs/${sdkVersion}")
    mkdir("${projectDir}/docs/${sdkVersion}")

    from("${buildDir}/docs/javadoc")
    into(layout.buildDirectory.dir("${projectDir}/docs/${sdkVersion}"))
    include("**/*.*")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}