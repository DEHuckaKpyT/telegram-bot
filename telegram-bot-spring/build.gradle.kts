import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val spring_version: String by project

plugins {
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

dependencies {
    //region springframework
    api("org.springframework.boot:spring-boot-starter:$spring_version")
    api("org.springframework.boot:spring-boot-starter-web:$spring_version")
    //endregion springframework

    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    //endregion dehuckakpyt

    //region other
    api("org.jetbrains.kotlin:kotlin-reflect")
    //endregion other

    //region springframework
    testApi("org.springframework.boot:spring-boot-starter-test:$spring_version")
    //endregion springframework
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
    }
}