val ktor_version: String by project
val koin_version: String by project

plugins {
    kotlin("jvm") version "1.8.22"
}

dependencies {
    api("io.ktor:ktor-server-core-jvm:$ktor_version")
    api("io.insert-koin:koin-ktor:$koin_version")
    api("io.insert-koin:koin-logger-slf4j:$koin_version")
    implementation(project(":telegram-bot-core"))
}