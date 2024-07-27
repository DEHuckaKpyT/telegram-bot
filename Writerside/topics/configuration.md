# Configuration

## All settings:

```kotlin
class TelegramBotConfig {

    /** Telegram bot token */
    var token: String? = null,
    /** Telegram bot username */
    var username: String? = null,
    /** Source for saving messages */
    var messageSource: (TelegramBotActualConfig.() -> MessageSource)? = null,
    /** Templater for build message templates */
    var templater: (TelegramBotActualConfig.() -> Templater)? = null,

    /** Configure receiving */
    fun receiving(block: UpdateReceiverConfig.() -> Unit)
}
```

```kotlin
class UpdateReceiverConfig {

    /** Source for saving callback.data */
    var callbackContentSource: (TelegramBotActualConfig.() -> CallbackContentSource)? = null,
    /** Source for saving chain state */
    var chainSource: (TelegramBotActualConfig.() -> ChainSource)? = null,
    /** Converter from object to string and back */
    var contentConverter: (TelegramBotActualConfig.() -> ContentConverter)? = null,
    /** Serializer from string to callback.data and back */
    var callbackSerializer: (TelegramBotActualConfig.() -> CallbackSerializer)? = null,
    /** Text templates for show to user when exception throws */
    var messageTemplate: (TelegramBotActualConfig.() -> MessageTemplate)? = null,
    /** Strategy for invoking BotHandler actions */
    var invocationStrategy: (TelegramBotActualConfig.() -> HandlerInvocationStrategy)? = null,
    /** Handler for catch internal exceptions */
    var exceptionHandler: (TelegramBotActualConfig.() -> ExceptionHandler)? = null,
    /** Handler for process exceptions in message chains */
    var chainExceptionHandler: (TelegramBotActualConfig.() -> ChainExceptionHandler)? = null,
            
    /** Activate and configure long polling for receiving updates */
    fun longPolling(block: LongPollingConfig.() -> Unit)
    /** Declare chain handlers */
    fun handling(block: BotHandling.() -> Unit)
    /** Declare any update handlers */
    fun update(block: BotUpdateHandling.() -> Unit)
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
                        // (for example, 'telegramBot', 'receiving.messageTemplate', 'templater')
                        exceptionHandler = { CustomExceptionHandler(telegramBot, receiving.messageTemplate, templater) }
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
                        // (for example, 'telegramBot', 'receiving.messageTemplate', 'templater')
                        exceptionHandler = { CustomExceptionHandler(telegramBot, receiving.messageTemplate, templater) }
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
                    // (for example, 'telegramBot', 'receiving.messageTemplate', 'templater')
                    exceptionHandler = { CustomExceptionHandler(telegramBot, receiving.messageTemplate, templater) }
                }
            }
            val context = TelegramBotFactory.createTelegramBotContext(config)
        </code-block>
    </tab>
</tabs>
