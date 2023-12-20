plugins {
    kotlin("jvm") version "1.8.22"
}

dependencies {
    //region dehuckakpyt
    api("io.github.dehuckakpyt:exposed-extensions:0.1.4")
    api(project(":telegram-bot-core"))
    //endregion dehuckakpyt
}