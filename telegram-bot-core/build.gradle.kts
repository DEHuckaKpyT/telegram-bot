val ktor_version: String by project
val jackson_version: String by project

plugins {
    kotlin("jvm") version "1.8.22"
}

dependencies {
    //region jackson
    api("com.fasterxml.jackson.core:jackson-databind:$jackson_version")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson_version")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jackson_version")
    //endregion jackson

    //region ktor
    api("io.ktor:ktor-client-apache-jvm:$ktor_version")
    api("io.ktor:ktor-client-content-negotiation-jvm:$ktor_version")
    api("io.ktor:ktor-serialization-jackson-jvm:$ktor_version")
    //endregion ktor

    //region other
    implementation("org.jsoup:jsoup:1.16.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    api("ch.qos.logback:logback-classic:1.4.14")
    //endregion other

    //region test
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.mockk:mockk:1.13.9")
    //endregion test
}

tasks.withType<Test> {
    useJUnitPlatform()

    jvmArgs(
        // https://mockk.io/doc/md/jdk16-access-exceptions.html
        "--add-opens", "java.base/java.time=ALL-UNNAMED",
        "--add-opens", "java.base/java.lang.reflect=ALL-UNNAMED"
    )
}
