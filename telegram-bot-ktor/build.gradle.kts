plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

dependencies {
    api(libs.ktor.server.core.jvm)
    api(libs.koin.ktor)
    api(libs.koin.logger.slf4j)
    implementation(project(":telegram-bot-core"))
}