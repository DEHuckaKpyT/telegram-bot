import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.22"
}

dependencies {
    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    //endregion dehuckakpyt

    //region other
    implementation("org.jsoup:jsoup:1.16.1")
    api("org.freemarker:freemarker:2.3.33")
    //endregion other

    //region test
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.mockk:mockk:1.13.9")
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
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
}