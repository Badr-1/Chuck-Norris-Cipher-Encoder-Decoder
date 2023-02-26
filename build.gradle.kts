import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        maven { url = uri("https://jitpack.io") }
    }
}

dependencies {
    implementation("org.fusesource.jansi:jansi:2.4.0")
    implementation ("org.jline:jline:3.21.0")
    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta9")
    implementation("com.github.kotlin-inquirer:kotlin-inquirer:0.1.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("Main")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "Main"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}