val koin_version: String by project
val exposed_version = "0.41.1"
val hikaricp_version = "5.0.1"
val postgresql_version = "42.5.4"

plugins {
    kotlin("jvm") version "1.8.22"
    id("com.google.devtools.ksp") version "1.8.22-1.0.11"
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    //region database
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")
    implementation("org.postgresql:postgresql:$postgresql_version")
    implementation("com.zaxxer:HikariCP:$hikaricp_version")
    //endregion database

    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    //endregion dehuckakpyt
}

tasks.test {
    useJUnitPlatform()
}