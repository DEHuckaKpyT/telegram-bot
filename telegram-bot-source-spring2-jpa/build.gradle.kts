val spring2_version: String by project

plugins {
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
}

dependencies {
    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    implementation(project(":telegram-bot-spring"))
    //endregion dehuckakpyt

    //region springframework
    api("org.springframework.boot:spring-boot-starter-data-jpa:$spring2_version")
    //endregion springframework

    //region database
    api("io.hypersistence:hypersistence-utils-hibernate-55:3.7.5")
    //endregion database

    //region test
    testImplementation("org.springframework.boot:spring-boot-starter-test:$spring2_version")
    testImplementation("com.github.database-rider:rider-spring:1.44.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    testImplementation("io.mockk:mockk:1.13.9")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
    testImplementation("org.testcontainers:postgresql:1.20.1")
    //endregion test

    //region temp
    testImplementation("org.postgresql:postgresql:42.7.3") //TODO remove when dependency will be resolved in "com.github.database-rider:rider-spring:1.44.0"
    testImplementation("org.apache.commons:commons-compress:1.27.0") //TODO remove when dependency will be resolved in "org.testcontainers:postgresql:1.20.1"
    testImplementation("com.jayway.jsonpath:json-path:2.9.0") //TODO remove when dependency will be resolved in "org.springframework.boot:spring-boot-starter-test:$spring2_version"
    //endregion temp
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}