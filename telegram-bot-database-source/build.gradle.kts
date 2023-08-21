plugins {
    kotlin("jvm") version "1.8.22"
    id("com.google.devtools.ksp") version "1.8.22-1.0.11"
}

dependencies {
    //region dehuckakpyt
    api("io.github.dehuckakpyt:exposed-extensions:0.1.3-SNAPSHOT")
    implementation(project(":telegram-bot-core"))
    //endregion dehuckakpyt
}

tasks.test {
    useJUnitPlatform()
}