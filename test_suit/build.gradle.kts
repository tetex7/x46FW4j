plugins {
    kotlin("jvm") version "1.9.0-Beta"
    `java-library`
}

group = "org.trs.test_suit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    api("org.apache.commons:commons-lang3:3.12.0")
    implementation(files("${rootDir}/x46FW/build/libs/x46FW-1.0-SNAPSHOT.jar"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}


tasks.jar {
    manifest {
        //attributes["Main-Class"] = "com.trs.x46FW.MainKt"
        attributes["Main-Class"] = "com.trs.test_suit.Main"
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

kotlin {
    jvmToolchain(20)
}

tasks.test {
    useJUnitPlatform()
}