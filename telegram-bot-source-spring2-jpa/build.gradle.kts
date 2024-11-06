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
    api("org.springframework.boot:spring-boot-starter-data-jpa:2.7.18")
    //endregion springframework

    //region database
    api(libs.hypersistence.utils.hibernate.get55())
    //endregion database

    //region test
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.18")
    testImplementation(libs.rider.spring)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.extensions.spring)
    testImplementation(libs.mockk)
    testImplementation(libs.springmockk)
    testImplementation(libs.org.testcontainers.postgresql)
    //endregion test
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}