plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":kscripts:script-definition"))
    implementation(libs.kotlin.scripting.common)
    implementation(libs.kotlin.scripting.jvm)
    implementation(libs.kotlin.scripting.jvm.host)
    runtimeOnly(libs.slf4j.nop)
}

tasks.test {
    useJUnitPlatform()
}
