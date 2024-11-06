import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.com.google.devtools.ksp)
}

dependencies {
    //region ktor
    implementation(libs.ktor.server.core.jvm)
    implementation(libs.ktor.server.netty.jvm)
    //endregion ktor

    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    implementation(project(":telegram-bot-ktor"))
    implementation(project(":telegram-bot-source-exposed"))
    //endregion dehuckakpyt

    //region other
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.java.time)
    implementation(libs.hikaricp)
    implementation(libs.org.postgresql.postgresql)
    compileOnly(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)
    //endregion other
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