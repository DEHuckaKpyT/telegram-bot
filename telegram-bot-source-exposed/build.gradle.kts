plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

dependencies {
    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    //endregion dehuckakpyt

    //region database
    api(libs.exposed.core)
    api(libs.exposed.dao)
    api(libs.exposed.java.time)
    api(libs.exposed.jdbc)
    api(libs.exposed.migration.jdbc)
    //endregion database
}