import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

dependencies {
    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    implementation(project(":telegram-bot-spring"))
    implementation(project(":telegram-bot-source-jpa"))
    implementation(project(":telegram-bot-templater-freemarker"))
    //endregion dehuckakpyt

    //region other
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("org.hibernate:hibernate-core:6.4.1.Final")
    //endregion other

    //region test
    testImplementation(project(":telegram-bot-test"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.github.database-rider:rider-spring:1.41.1")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    testImplementation("io.mockk:mockk:1.13.9")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
    testImplementation("org.testcontainers:postgresql:1.20.1")
    //endregion test

    //region temp
    testImplementation("org.apache.commons:commons-compress:1.27.0") //TODO remove when dependency will be resolved in "org.testcontainers:postgresql:1.20.1"
    //endregion temp
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
}

sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}
