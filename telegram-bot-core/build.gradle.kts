plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

dependencies {
    //region jackson
    api(libs.jackson.databind)
    api(libs.jackson.module.kotlin)
    api(libs.jackson.datatype.jsr310)
    //endregion jackson

    //region ktor
    api(libs.ktor.client.apache.jvm)
    api(libs.ktor.client.content.negotiation.jvm)
    api(libs.ktor.serialization.jackson.jvm)
    //endregion ktor

    //region other
    implementation(libs.jsoup)
    api(libs.kotlinx.coroutines.core)
    api(libs.logback.classic)
    //endregion other

    //region test
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.mockk)
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
