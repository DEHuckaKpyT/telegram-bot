# Kotlin Telegram Bot

[![Maven Central](https://img.shields.io/maven-central/v/io.github.dehuckakpyt.telegrambot/telegram-bot-core?label=Maven%20Central&logo=apache-maven&logoColor=red&color=red)](https://central.sonatype.com/artifact/io.github.dehuckakpyt.telegrambot/telegram-bot-core)
[![Documentation](https://img.shields.io/badge/Writerside-docs-forest?logo=jetbrains&logoColor=black)](https://dehuckakpyt.github.io/telegram-bot/starter-topic.html)

[![Telegram Bot API version](https://img.shields.io/badge/Bot_Api-7.8-blue?logo=telegram)](https://core.telegram.org/bots/api#july-31-2024)
[![GitHub License](https://img.shields.io/github/license/DEHuckaKpyT/telegram-bot?logo=apache&logoColor=red&label=License&color=orange)](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/LICENSE.txt)

Kotlin library for creating Telegram Bots. You can use clean version, with implementation for [Spring](https://spring.io/), [Ktor](https://ktor.io/)+[Koin](https://insert-koin.io/) or create with you own implementation.
It have also possibility to save state in database with [Spring JPA](https://spring.io/projects/spring-data-jpa/) or [Exposed](https://github.com/JetBrains/Exposed).

[Full documentation with examples and explanations](https://dehuckakpyt.github.io/telegram-bot/starter-topic.html).

Example of applications in [example-spring](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-spring), [example-ktor](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-ktor), [example-core](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-core) directories.

## Why this library
- Focused on building a **dialog with the user** (for example, no need to specify `chatId` in dialog chains).
- Has **many useful utilities** (such as templating, keyboard and button creating and other).
- Telegram API methods realization have **overloads** for more comfortable usage (like a `chatId` as `String` or `Long`).
- Working on **coroutines**.
- Has **clean** version or with **Spring** or **Ktor+Koin** frameworks.
- Has possibility to **save state in database** with **Spring JPA** or **Exposed**.
- Easy to **write tests** for your bot.

## ⚠️ Limitations (will be resolved) ⚠️
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

Can be used for integrate with any frameworks manually.

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
    
    // start and stop for example only, use this methods with starting and stopping your application
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

### Spring version

Ready-to-use solution for use with Spring.

`build.gradle.kts`
```kotlin
repositories {
    mavenCentral()
}
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:$telegram_bot_version")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-spring:$telegram_bot_version")
}
```
`com/example/myproject/config/BotConfig.kt`
```kotlin
@EnableTelegramBot
@Configuration
class BotConfig {

    @Bean //optional bean
    fun telegramBotConfig(): TelegramBotConfig = TelegramBotConfig().apply {
        //configure..
    }
}
```
`resources/application.properties`
```
telegram-bot.token=${TELEGRAM_BOT_TOKEN}
telegram-bot.username=${TELEGRAM_BOT_USERNAME}
```
`com/example/myproject/handler/StartHandler.kt`
```kotlin
@HandlerComponent
class StartHandler : BotHandler({
    command("/start") {
        sendMessage("Hello, my name is ${bot.username} :-)")
    }
})
```

### Ktor+Koin version

Ready-to-use solution for use with Ktor+Koin.

`build.gradle.kts`
```kotlin
repositories {
    mavenCentral()
}
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:$telegram_bot_version")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-ktor:$telegram_bot_version")
}
```
`com/example/myproject/KtorApp.kt`
```kotlin
fun Application.module() {
    install(Koin) {
        modules(defaultModule)
    }
    install(TelegramBot) {
        //configure..
    }
}
```
`resources/application.conf`
```
telegram-bot {
    token = ${TELEGRAM_BOT_TOKEN}
    username = ${TELEGRAM_BOT_USERNAME}
}
```
`com/example/myproject/handler/StartHandler.kt`
```kotlin
@Factory
class StartHandler : BotHandler({
    command("/start") {
        sendMessage("Hello, my name is ${bot.username} :-)")
    }
})
```


## Database integration

> [!WARNING]  
> And it is <b>highly recommended</b> to use with <b>saving states in database</b>.
> There are ready-to-use solutions for <a href="spring-jpa.md">Spring JPA</a> and <a href="exposed.md">Exposed</a>.
> Interfaces to be implemented with saving to the database described <a href="sources.md">here</a>.

### Spring JPA

All you have to do is add a dependency for `source-jpa`:

#### Spring 3.x
```kotlin
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:$telegram_bot_version")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-spring:$telegram_bot_version")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-source-jpa:$telegram_bot_version")
}
```
#### Spring 2.7
```kotlin
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:$telegram_bot_version")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-spring:$telegram_bot_version")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-source-spring2-jpa:$telegram_bot_version")
}
```

#### Available properties

| PROPERTY                                                   | DEFAULT | DESCRIPTION                                                          |
|------------------------------------------------------------|---------|----------------------------------------------------------------------|
| `telegram-bot.source-jpa.enabled`                          | `true`  | Disable all default sources                                          |
| `telegram-bot.source-jpa.message-source.enabled`           | `true`  | Disable default message source                                       |
| `telegram-bot.source-jpa.chain-source.enabled`             | `true`  | Disable default chain source                                         |
| `telegram-bot.source-jpa.callback-content-source.enabled`  | `true`  | Disable default callback content source                              |
| `telegram-bot.source-jpa.callback-content-source.per-user` | `20`    | Max count of contents will be saved for every user (`-1` for ignore) |

### Exposed

To save the state in the database, it is necessary to connect to it using [Exposed](https://github.com/JetBrains/Exposed) ([example](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/example/src/main/kotlin/io/github/dehuckakpyt/telegrambotexample/plugin/DatabaseConnection.kt)).
Then add the dependency and specify the sources:

```kotlin
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:$telegram_bot_version")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-source-exposed:$telegram_bot_version")
}
```
```kotlin
val config = TelegramBotConfig().apply {
    messageSource = { MessageSource.inDatabase }
    receiving {
        callbackContentSource = {
            CallbackContentSource.inDatabase(
                maxCallbackContentsPerUser = 20
            )
        }
        chainSource = { ChainSource.inDatabase }
    }
}
```

[🔗Full documentation with examples and explanations🔗](https://dehuckakpyt.github.io/telegram-bot/starter-topic.html).
