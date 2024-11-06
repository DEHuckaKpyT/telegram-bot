plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.spring)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.jpa)
}

dependencies {
    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    implementation(project(":telegram-bot-spring"))
    //endregion dehuckakpyt

    //region springframework
    api(libs.spring.boot.starter.data.jpa)
    //endregion springframework

    //region database
    api(libs.hypersistence.utils.hibernate.get63())
    //endregion database

    //region test
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.rider.spring)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.extensions.spring)
    testImplementation(libs.mockk)
    testImplementation(libs.springmockk)
    testImplementation(libs.org.testcontainers.postgresql)
    //endregion test
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}