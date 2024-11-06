plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.scripting.common)
    implementation(libs.kotlin.scripting.jvm)
    implementation(libs.kotlin.scripting.dependencies)
    implementation(libs.kotlin.scripting.dependencies.maven)
    implementation(libs.kotlinx.coroutines.core)
}

tasks.test {
    useJUnitPlatform()
}