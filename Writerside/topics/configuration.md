# Configuration

## All settings:

```kotlin
data class TelegramBotConfig(
    /** Telegram bot token */
    var token: String? = null,
    /** Telegram bot username */
    var username: String? = null,
    /** Telegram bot client additional configuration */
    var clientConfiguration: (HttpClientConfig<Apache5EngineConfig>.() -> Unit)? = null,
    /** Source for saving messages */
    var telegramMessageSource: (TelegramBotActualConfig.() -> TelegramMessageSource<out TelegramMessage<out Any>>)? = null,
    /** Templater for build message templates */
    var templater: (TelegramBotActualConfig.() -> Templater)? = null,
    val receiving: UpdateReceiverConfig = UpdateReceiverConfig(),
    /** Configure startup edit behavior (for example, bot menu commands) */
    val edit: EditConfig = EditConfig(),
) {
    /** Telegram bot client additional configuration */
    fun client(block: HttpClientConfig<Apache5EngineConfig>.() -> Unit) {
        clientConfiguration = block
    }
    /** Configure receiving */
    fun receiving(block: UpdateReceiverConfig.() -> Unit) {
        receiving.apply(block)
    }
    /** Configure startup edit behavior */
    fun edit(block: EditConfig.() -> Unit) {
        edit.apply(block)
    }
}
```

```kotlin
data class UpdateReceiverConfig(

    /** Source for saving callback.data */
    var callbackContentSource: (TelegramBotActualConfig.() -> CallbackContentSource)? = null,
    /** Source for saving chain state */
    var chainSource: (TelegramBotActualConfig.() -> ChainSource)? = null,
    /** Source for saving users */
    var telegramUserSource: (TelegramBotActualConfig.() -> TelegramUserSource)? = null,
    /** Source for saving chats (except private) */
    var telegramChatSource: (TelegramBotActualConfig.() -> TelegramChatSource)? = null,
    /** Source for saving all changes of bot`s status in all chats */
    var telegramChatStatusEventSource: (TelegramBotActualConfig.() -> TelegramChatStatusEventSource)? = null,
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
    /** Receiver for getting and handling updates */
    var updateReceiver: (TelegramBotActualConfig.() -> UpdateReceiver)? = null,
    /** Handlers declaration */
    var handling: BotHandling.() -> Unit = {},
    /** Update handlers declaration */
    var updateHandling: BotUpdateHandling.() -> Unit = {},
    /** Long polling settings */
    val longPolling: LongPollingConfig = LongPollingConfig(),
    /** Webhook settings */
    val webhook: WebhookConfig = WebhookConfig(),
    /** Receiving mode (LONG_POLLING / WEBHOOK) */
    var mode: ReceivingMode? = null,
    /** Activate and configure long polling for receiving updates */
    fun longPolling(block: LongPollingConfig.() -> Unit)
    /** Activate and configure webhook for receiving updates */
    fun webhook(block: WebhookConfig.() -> Unit)
    /** Declare chain handlers */
    fun handling(block: BotHandling.() -> Unit)
    /** Declare any update handlers */
    fun update(block: BotUpdateHandling.() -> Unit)
)
```

## Startup bot changes

Bot changes applied during application startup are described on a separate page:
<a href="startup-edits.md">Startup bot edits</a>.

## Configuration sources and priority

You can configure the bot using:

- code (`TelegramBotConfig`)
- application properties/config (`application.*`) for Spring and Ktor
- classpath file `telegram-bot.yaml`

Priority is:

`code > application config > telegram-bot.yaml > defaults`

Example of `telegram-bot.yaml`:

```yaml
telegram-bot:
  token: ${TELEGRAM_BOT_TOKEN}
  username: ${TELEGRAM_BOT_USERNAME:}
  receiving:
    mode: long-polling
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
                    // customize bot client
                    client {
                        defaultRequest {
                            url {
                                host = "my.custom.server.url"
                                port = 1234
                            }
                        }
                    }
                    eventListening {
                        after method "getUpdates" called { args ->
                            val offset: Long? by args
                            val limit: Int? by args
                            val timeout: Int? by args
                            val allowedUpdates: Iterable&lt;String&gt;? by args
                            logger.info("Called \"getUpdates\" while long polling with offset = $offset, limit = $limit, timeout = $timeout, allowedUpdates = $allowedUpdates")
                        }
                    }
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
                    // customize bot client
                    client {
                        defaultRequest {
                            url {
                                host = "my.custom.server.url"
                                port = 1234
                            }
                        }
                    }
                    eventListening {
                        after method "getUpdates" called { args ->
                            val offset: Long? by args
                            val limit: Int? by args
                            val timeout: Int? by args
                            val allowedUpdates: Iterable&lt;String&gt;? by args
                            logger.info("Called \"getUpdates\" while long polling with offset = $offset, limit = $limit, timeout = $timeout, allowedUpdates = $allowedUpdates")
                        }
                    }
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
                    // customize bot client
                    client {
                        defaultRequest {
                            url {
                                host = "my.custom.server.url"
                                port = 1234
                            }
                        }
                    }
                    eventListening {
                        after method "getUpdates" called { args ->
                            val offset: Long? by args
                            val limit: Int? by args
                            val timeout: Int? by args
                            val allowedUpdates: Iterable&lt;String&gt;? by args
                            logger.info("Called \"getUpdates\" while long polling with offset = $offset, limit = $limit, timeout = $timeout, allowedUpdates = $allowedUpdates")
                        }
                    }
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
