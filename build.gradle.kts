import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.22"
    `java-library`
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0-rc-1"
}

allprojects {
    group = "io.github.dehuckakpyt.telegrambot"
    version = "0.7.2"
}

subprojects {
    repositories {
        mavenCentral()
    }
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

configure(subprojects.filter { it.path.startsWith(":example").not() }) {
//    kotlin {
//        explicitApi()
//    }

    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")

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
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
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

    java {
        withJavadocJar()
        withSourcesJar()
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
}