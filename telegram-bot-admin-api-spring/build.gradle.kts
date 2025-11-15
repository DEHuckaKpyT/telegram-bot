import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.spring)

    kotlin("kapt")
}

dependencies {
    //region springframework
    api(libs.spring.boot.starter)
    api(libs.spring.boot.starter.webflux)
    //endregion springframework

    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    implementation(project(":telegram-bot-spring"))
    //endregion dehuckakpyt

    //region kotlin
    api(libs.kotlin.reflect)
    api(libs.kotlinx.coroutines.reactor)
    //endregion kotlin

    //region mapstruct
    api("org.mapstruct:mapstruct:1.6.0")
    kapt("org.mapstruct:mapstruct-processor:1.6.0")
    //endregion mapstruct

    //region springframework
    testApi(libs.spring.boot.starter.test)
    //endregion springframework
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
    }
}

kapt {
    arguments {
        arg("mapstruct.defaultComponentModel", "spring")
        arg("mapstruct.unmappedTargetPolicy", "IGNORE")
    }
}
