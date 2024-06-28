val kotlin_version: String by project

plugins {
    kotlin("jvm") version "1.8.22"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":kscripts:script-definition"))
    implementation("org.jetbrains.kotlin:kotlin-scripting-common:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm-host:$kotlin_version")
    runtimeOnly("org.slf4j:slf4j-nop:1.7.28")
}

tasks.test {
    useJUnitPlatform()
}
