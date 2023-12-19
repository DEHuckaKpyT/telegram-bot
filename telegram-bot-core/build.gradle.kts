val ktor_version: String by project
val koin_version: String by project

plugins {
    kotlin("jvm") version "1.8.22"
}

dependencies {
    api("io.ktor:ktor-server-core-jvm:$ktor_version")
    api("org.freemarker:freemarker:2.3.32")
    implementation("org.jsoup:jsoup:1.16.1")

    //region koin
    compileOnlyApi("io.insert-koin:koin-core-coroutines:$koin_version")
    api("io.insert-koin:koin-ktor:$koin_version")
    api("io.insert-koin:koin-logger-slf4j:$koin_version")
    //endregion koin

    //region dehuckakpyt
    api("io.github.dehuckakpyt:micro-service-core:0.1.4")
    //endregion dehuckakpyt
}