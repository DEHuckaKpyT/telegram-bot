import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.spring)
}

dependencies {
    //region springframework
    api(libs.spring.boot.starter)
    api(libs.spring.boot.starter.webflux)
    //endregion springframework

    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    //endregion dehuckakpyt

    //region kotlin
    api(libs.kotlin.reflect)
    api(libs.kotlinx.coroutines.reactor)
    //endregion kotlin

    //region springframework
    testApi(libs.spring.boot.starter.test)
    //endregion springframework
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
    }
}