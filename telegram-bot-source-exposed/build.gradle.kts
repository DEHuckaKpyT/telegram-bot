plugins {
    kotlin("jvm") version "1.8.22"
}

dependencies {
    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    //endregion dehuckakpyt

    //region database
    api("org.jetbrains.exposed:exposed-core:0.50.0")
    api("org.jetbrains.exposed:exposed-dao:0.50.0")
    api("org.jetbrains.exposed:exposed-java-time:0.50.0")
    api("org.jetbrains.exposed:exposed-jdbc:0.50.0")
    api("org.jetbrains.exposed:exposed-json:0.50.0")
    //endregion database
}