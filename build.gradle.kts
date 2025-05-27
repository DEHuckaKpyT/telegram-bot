import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    `java-library`
    `maven-publish`
    signing
    alias(libs.plugins.io.github.gradle.nexus.publish.plugin)
}

allprojects {
    group = "io.github.dehuckakpyt.telegrambot"
    version = "0.13.0"
}

allprojects {
    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

kotlin {
    jvmToolchain(17)
}

nexusPublishing {
    packageGroup.set(project.group as String)

    repositories {
        sonatype {  //only for users registered in Sonatype after 24 Feb 2021
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

            username.set(project.properties["sonatypeUsername"].toString())
            password.set(project.properties["sonatypePassword"].toString())
        }
    }
}

configure(subprojects.filter { it.path.startsWith(":example").not() && it.path.startsWith(":kscripts").not() }) {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    publishing {
        publications {
            create<MavenPublication>(project.name) {
                groupId = project.group as String
                artifactId = project.name
                version = project.version as String

                from(components["java"])

                pom {
                    packaging = "jar"
                    name.set(project.name)
                    description.set("Kotlin Telegram Bot library")
                    url.set("https://github.com/DEHuckaKpyT/telegram-bot")
                    scm {
                        connection.set("scm:https://github.com/DEHuckaKpyT/telegram-bot.git")
                        developerConnection.set("scm:git@github.com:DEHuckaKpyT/telegram-bot.git")
                        url.set("https://github.com/DEHuckaKpyT/telegram-bot")
                    }
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("DEHuckaKpyT")
                            name.set("Denis Matytsin")
                            email.set("den-matytsin@mail.com")
                        }
                    }
                }
                repositories {
                    maven {
                        val releasesUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                        val snapshotsUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                        url = if (version.toString().endsWith("SNAPSHOT")) snapshotsUrl else releasesUrl
                        credentials {
                            username = project.properties["sonatypeUsername"].toString()
                            password = project.properties["sonatypePassword"].toString()
                        }
                    }
                }
            }
        }
    }

    signing {
        sign(publishing.publications[project.name])
    }

    kotlin {
//        explicitApi()
    }

    java {
        withJavadocJar()
        withSourcesJar()
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.test {
        useJUnitPlatform()
    }

    sourceSets.main {
        java.srcDirs("build/generated/ksp/main/kotlin")
    }
}

// Experimental
tasks.register("updateProjectVersionInWriterside") {
    doFirst {
        // write project version to Writerside variables
        val writersideVariablesFile = file("Writerside/v.list")
        val writersideVariables = writersideVariablesFile.readText()
            .replace(Regex("<var name=\"current_version\" value=\".*\"/>"), "<var name=\"current_version\" value=\"${project.version}\"/>")
        writersideVariablesFile.writeText(writersideVariables)

        // write project version to Writerside config
        val writersideConfigFile = file("Writerside/writerside.cfg")
        val writersideConfig = writersideConfigFile.readText()
            .replace(Regex("<instance src=\"ktb.tree\" web-path=\"/ktb/\" version=\".*\"/>"), "<instance src=\"ktb.tree\" web-path=\"/ktb/\" version=\"${project.version}\"/>")
        writersideConfigFile.writeText(writersideConfig)
    }
}

tasks.build {
    dependsOn("updateProjectVersionInWriterside")
}
