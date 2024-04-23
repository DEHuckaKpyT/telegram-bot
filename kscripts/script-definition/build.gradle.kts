val kotlin_version: String by project
val kotlin_coroutines_version: String by project

plugins {
    kotlin("jvm") version "1.8.22"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-scripting-common:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-scripting-dependencies:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-scripting-dependencies-maven:$kotlin_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines_version")
}

tasks.test {
    useJUnitPlatform()
}