# Exception handling

## Chain errors

To change the default error messages, you need to implement class `MessageTemplate`. For example:

```kotlin
class CustomMessageTemplate : MessageTemplate() {
    override val whenCommandNotFound: String = "Unknown command \${command}. See /help."
    override val whenUnexpectedMessageType: String = "Unexpected message type."
    override val whenStepNotFound: String = "Unexpected message. See /help."
    override val whenKnownException: String = "\${message}"
    override val whenUnknownException: String = "Unexpected error. Contact with developers."
}
```

And set it:

```kotlin
val config = TelegramBotConfig().apply {
    receiving {
        messageTemplate = { CustomMessageTemplate() }
    }
}
```

For **Spring** and **Ktor+Koin** you can simply set messages in properties like:

```
telegram-bot.template.when-command-not-found=Unknown command ${command}. See /help.
telegram-bot.template.when-unexpected-message-type=Unexpected message type.
telegram-bot.template.when-step-not-found=Unexpected message. See /help.
telegram-bot.template.when-known-exception="${message}"
telegram-bot.template.when-unknown-exception=Unknown command. See /help.
```

## General exceptions

`exceptionHandler: ExceptionHandler` - interface to implementation.
Also, you can inherit from `ExceptionHandlerImpl` and add you custom exceptions only.

```kotlin
class CustomExceptionHandler(bot: TelegramBot, template: MessageTemplate, templater: Templater) :
    ExceptionHandlerImpl(bot, template, templater) {

    override suspend fun caught(chatId: Long, ex: Throwable) {
        when (ex) {
            is CustomException -> bot.sendMessage(chatId, ex.localizedMessage)
            else -> super.caught(chatId, ex)
        }
    }
}
```

```kotlin
val config = TelegramBotConfig().apply {
    receiving {
        exceptionHandler = { CustomExceptionHandler(telegramBot, receiving.messageTemplate, templating.templater) }

        handling {
            startCommand()
        }
    }
}

fun BotHandling.startCommand() {
    command("/start") {
        throw CustomException("this message will now be displayed to the user")
    }
}
```