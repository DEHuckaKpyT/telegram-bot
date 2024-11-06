import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

dependencies {
    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    //endregion dehuckakpyt

    //region test
    api(libs.mockk)
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