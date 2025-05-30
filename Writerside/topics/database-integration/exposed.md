# Exposed

To save the state in the database, it is necessary to connect to it using [Exposed](https://github.com/JetBrains/Exposed) ([example](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/example/src/main/kotlin/io/github/dehuckakpyt/telegrambotexample/plugin/DatabaseConnection.kt)).
Then add the dependency and specify the sources:
```kotlin
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:%current_version%")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-source-exposed:%current_version%")
}
```
```kotlin
val config = TelegramBotConfig().apply {
    messageSource = { MessageSource.inDatabase }
    receiving {
        callbackContentSource = { CallbackContentSource.inDatabase(maxCallbackContentsPerUser = 20) }
        chainSource = { ChainSource.inDatabase }
        telegramUserSource = { TelegramUserSource.inDatabase }
        telegramChatSource = { TelegramChatSource.inDatabase }
        telegramChatStatusEventSource = { TelegramChatStatusEventSource.inDatabase }
    }
}
```
