# Configuration

## All settings:

```kotlin
class TelegramBotConfig {
    // telegram bot token
    var token: String
    // telegram bot username - need for command matching in group chats
    var username: String

    // source for save messages from user and bot
    var messageSource: MessageSource?

    fun templating(block: TelegramBotTemplatingConfig.() -> Unit)

    fun receiving(block: UpdateReceiverConfig.() -> Unit)
}
```

```kotlin
class TelegramBotTemplatingConfig {
    // formatter for methods 'cleanHtml()' and 'escapeHtml()' in templating
    var htmlFormatter: HtmlFormatter?

    // configuration for freemarker library
    fun freemarker(block: Configuration.() -> Unit)
}
```

```kotlin
class UpdateReceiverConfig {
    // source for working callback data with more than 64 symbols
    var callbackContentSource: CallbackContentSource?
    // source for save chain state
    var chainSource: ChainSource?
    // delimiter for callback data values (using in callbackSerializer)
    var callbackDataDelimiter: Char?
    // converter from/to string callback data (using in callbackSerializer)
    var contentConverter: ContentConverter?
    // serializer for callback data - need for working callback buttons
    var callbackSerializer: CallbackSerializer?
    // messages to be display to the user when an internal exception throws
    var messageTemplate: MessageTemplate?
    // handler for processing exceptions in handlers
    var exceptionHandler: ExceptionHandler?
    // handler for processing internal errors (like unknown command)
    var chainExceptionHandler: ChainExceptionHandler?

    // configuration for long polling (webhook coming soon)
    fun longPolling(block: LongPollingConfig.() -> Unit)

    // handler registration
    fun handling(block: BotHandling.() -> Unit)
}
```

## Set your custom implementations

To set your own implementation, it is enough to set a lambda with its creation. 
You can use already initialized elements for creation.

<tabs id="simple-example" group="telegram-bot-code">
    <tab title="Spring" group-key="spring">
        <code-block lang="kotlin">
            @EnableTelegramBot
            @Configuration
            class BotConfig {
                @Bean
                fun telegramBotConfig(): TelegramBotConfig = TelegramBotConfig().apply {
                    receiving {
                        // you can use other initialized elements in lambda
                        // (for example, 'telegramBot', 'receiving.messageTemplate', 'templating.templater')
                        exceptionHandler = { CustomExceptionHandler(telegramBot, receiving.messageTemplate, templating.templater) }
                    }
                }
            }
        </code-block>
    </tab>
    <tab title="Ktor + Koin" group-key="ktor">
        <code-block lang="kotlin">
            fun Application.configureTelegramBot() {
                install(TelegramBot) {
                    receiving {
                        // you can use other initialized elements in lambda
                        // (for example, 'telegramBot', 'receiving.messageTemplate', 'templating.templater')
                        exceptionHandler = { CustomExceptionHandler(telegramBot, receiving.messageTemplate, templating.templater) }
                    }
                }
            }
        </code-block>
    </tab>
    <tab title="Core" group-key="core">
        <code-block lang="kotlin">
            val config = TelegramBotConfig().apply {
                receiving {
                    // you can use other initialized elements in lambda
                    // (for example, 'telegramBot', 'receiving.messageTemplate', 'templating.templater')
                    exceptionHandler = { CustomExceptionHandler(telegramBot, receiving.messageTemplate, templating.templater) }
                }
            }
            val context = TelegramBotFactory.createTelegramBotContext(config)
        </code-block>
    </tab>
</tabs>
