/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.2/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("jvm") version "1.9.0-Beta"
    id ("org.jetbrains.kotlin.plugin.allopen").version("1.8.22")

    // Apply the application plugin to add support for building a CLI application in Java.
    `java-library`
}

val PRER = false
val VID = "1.1"


group = "com.trs.x46FW"
version = if (PRER) "$VID-SNAPSHOT" else VID

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // Use the JUnit 5 integration.
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")



    api("com.google.guava:guava:31.1-jre")
    //implementation("commons-cli:commons-cli:1.5.0")
    implementation("org.json:json:20230227")
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    api("org.apache.commons:commons-lang3:3.12.0")
    //
    //api("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}

// Apply a specific Java toolchain to ease working on different environments.

kotlin {
    jvmToolchain(20)
}

allOpen {
    annotation("com.trs.x46FW.internal.x46FW_API")
    // annotations("com.another.Annotation", "com.third.Annotation")
}

val p = project
tasks.jar {
    manifest {
        //attributes["Main-Class"] = "com.trs.x46FW.MainKt"
        attributes["Implementation-Title"] = project.name
        attributes["dev_name"] = "tetex7"
        attributes["Implementation-Version"] = project.version
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    doLast()
    {
        //@Suppress("Deprecated")
        println("JAR NAME:\n\t\"${rootProject.name}-${version}.jar\"")
        //p.task<Test>("test")
        //println("PAK NAME:\n\t\"${group.}\"")
    }
}


tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
