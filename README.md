# Kotlin Telegram Bot

[![Maven Central](https://img.shields.io/maven-central/v/io.github.dehuckakpyt.telegrambot/telegram-bot-core?label=Maven%20Central&logo=apache-maven&logoColor=red&color=red)](https://central.sonatype.com/artifact/io.github.dehuckakpyt.telegrambot/telegram-bot-core)
[![Documentation](https://img.shields.io/badge/Writerside-docs-forest?logo=jetbrains&logoColor=black)](https://dehuckakpyt.github.io/telegram-bot/starter-topic.html)

[![Telegram Bot API version](https://img.shields.io/badge/Bot_Api-7.7-blue?logo=telegram)](https://core.telegram.org/bots/api#july-7-2024)
[![GitHub License](https://img.shields.io/github/license/DEHuckaKpyT/telegram-bot?logo=apache&logoColor=red&label=License&color=orange)](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/LICENSE.txt)

Kotlin library for creating Telegram Bots. You can use clean version, with implementation for [Spring](https://spring.io/), [Ktor](https://ktor.io/)+[Koin](https://insert-koin.io/) or create with you own implementation.
It have also possibility to save state in database with [Spring JPA](https://spring.io/projects/spring-data-jpa/) or [Exposed](https://github.com/JetBrains/Exposed).

[Full documentation](https://dehuckakpyt.github.io/telegram-bot/starter-topic.html) with examples and explanations.

Example of applications in [example-spring](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-spring), [example-ktor](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-ktor), [example-core](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-core) directories.

## Why this library
- Focused on building a **dialog with the user** (for example, no need to specify `chatId` in dialog chains).
- Has **many useful utilities** (such as templating, keyboard and button creating and other).
- Telegram API methods realization have **overloads** for more comfortable usage (like a `chatId` as `String` or `Long`).
- Working on **coroutines**.
- Has **clean** version or with **Spring** or **Ktor+Koin** frameworks.
- Has possibility to **save state in database** with **Spring JPA** or **Exposed**.
- Easy to **write tests** for your bot.

## ⚠️ Caveat at this moment (will be resolved) ⚠️
- Now available only long polling (will be added webhook also).

## Prerequisites

- JDK 17 or higher
- Kotlin 1.8 or higher
- Gradle or Maven


## Quick start

`build.gradle.kts`
```kotlin
repositories {
    mavenCentral()
}
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:$telegram_bot_version")
}
```

### Core version

`com/example/myproject/App.kt`
```kotlin
fun main(args: Array<String>): Unit {
    val config = TelegramBotConfig().apply {
        token = "<bot token required>"
        username = "<bot username required>"
        
        receiving {
            handling {
                startCommand()
            }
        }
    }
    val context = TelegramBotFactory.createTelegramBotContext(config)
    val updateReceiver = context.updateReceiver
    // get telegramBot, templater, buttonFactory and other from created context...
    
    updateReceiver.start()
    readlnOrNull()
    updateReceiver.stop()
}
```
`com/example/myproject/handler/StartHandler.kt`
```kotlin
fun BotHandling.startCommand() {
    command("/start", next = "get_name") {
        // you don't have to specify a chatId to send messages
        sendMessage("Hello, my name is ${bot.username} :-)")
        // but you can do it
        bot.sendMessage(chatId, "And what is your name?")
    }

    step("get_name") {
        sendMessage("Nice to meet you, $text!")
    }
}
```

### Spring and Ktor+Koin

Get started in [documentation](https://dehuckakpyt.github.io/telegram-bot/get-started.html).