import com.github.gradle.node.npm.task.NpmTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.spring)
    id("com.github.node-gradle.node") version "7.0.2"
}

dependencies {
    //region springframework
    api(libs.spring.boot.starter)
    api(libs.spring.boot.starter.webflux)
    implementation(libs.spring.boot.starter.security)
    //endregion springframework

    //region dehuckakpyt
    implementation(project(":telegram-bot-core"))
    implementation(project(":telegram-bot-spring"))
    implementation(project(":telegram-bot-admin-api-spring"))
    //endregion dehuckakpyt

    //region kotlin
    api(libs.kotlin.reflect)
    api(libs.kotlinx.coroutines.reactor)
    //endregion kotlin

    //region test
    testApi(libs.spring.boot.starter.test)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.extensions.spring)
    testImplementation(libs.mockk)
    testImplementation(libs.springmockk)
    //endregion test
}

node {
    version.set(libs.versions.node)
    npmVersion.set(libs.versions.npm)
    download.set(true)
    nodeProjectDir.set(file("./src/main/webapp"))
}

// Build frontend and copy to 'build/resources/static'
tasks.register<NpmTask>("viteBuild") {
    dependsOn(tasks.npmInstall)
    args.set(listOf("run", "build"))
    workingDir.set(file("./src/main/webapp"))
}
tasks.named<ProcessResources>("processResources") {
    dependsOn("viteBuild")

    from("./src/main/webapp/dist") {
        into("static")
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
    }
}
