import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.org.springframework.boot)
    alias(libs.plugins.io.spring.dependency.management)
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.spring)
}

dependencies {
    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    implementation(project(":telegram-bot-spring"))
    implementation(project(":telegram-bot-source-jpa"))
    implementation(project(":telegram-bot-templater-freemarker"))
    //endregion dehuckakpyt

    //region other
    implementation(libs.hikaricp)
    implementation(libs.org.postgresql.postgresql)
    implementation(libs.hibernate.core)
    //endregion other

    //region test
    testImplementation(project(":telegram-bot-test"))
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.rider.spring)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.extensions.spring)
    testImplementation(libs.mockk)
    testImplementation(libs.springmockk)
    testImplementation(libs.org.testcontainers.postgresql)
    //endregion test
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
}

sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}
