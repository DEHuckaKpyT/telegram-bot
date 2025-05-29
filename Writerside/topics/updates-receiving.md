# Updates receiving

## Long polling

Long polling is used by default. You don't need any movement to make the bot work.

It can be configured easy:

```kotlin
val config = TelegramBotConfig().apply {
    receiving {
        longPolling {
            limit = 10 // default null
            timeout = 25 // default 30
            retryDelay = 5_000 // default 5,000
            // default CoroutineScope(Dispatchers.Default + SupervisorJob() + CoroutineName("TelegramBot"))
            scope = CoroutineScope(Dispatchers.Default.limitedParallelism(10) + SupervisorJob())
        }
    }
}
```

That`s all. Bot works.

## Webhook

Now available only for Spring. *But don't get too upset. More details in <a href="updates-receiving.md#custom-receiver">"Custom receiver"</a>.

<tabs id="template-factory-receiving-templates" group="telegram-bot-code">
    <tab title="Spring" group-key="spring"></tab>
</tabs>

It can be configured easy too:

```yaml
telegram-bot:
  spring:
    update-receiver: webhook
    # URL to your bot application
    update-receiver.webhook.url.host: "https://my.domain.com/api/my-awesome-bot"
```

By default, unique random token will be generated for `X-Telegram-Bot-Api-Secret-Token` header.
By default, full URL will be concatenated to `https://my.domain.com/api/my-awesome-bot/updates/receive`.

All available properties:

| PROPERTY                                                                                      | DEFAULT            | DESCRIPTION                                                                          |
|-----------------------------------------------------------------------------------------------|--------------------|--------------------------------------------------------------------------------------|
| `telegram-bot.spring.update-receiver`                                                         | `long-polling`     | Choosing long polling or webhook.                                                    |
| `telegram-bot.spring.update-receiver.webhook.url.host`                                        | -                  | `Required` URL directly to your application. Will be concatenated with `urlPath`.    |
| `telegram-bot.spring.update-receiver.webhook.url.path`                                        | `/updates/receive` | API mapping in application. Will be concatenated with `urlHost`.                     |
| `telegram-bot.spring.update-receiver.webhook.certificate.path`                                | -                  | Path to certificate in resources. For example, `/sert.pem`.                          |
| `telegram-bot.spring.update-receiver.webhook.ip-address`                                      | -                  | The fixed IP address instead of the IP address resolved through DNS.                 |
| `telegram-bot.spring.update-receiver.webhook.max-connections`                                 | -                  | The maximum allowed number of simultaneous HTTPS connections.                        |
| `telegram-bot.spring.update-receiver.webhook.drop-pending-updates`                            | -                  | Pass **True** to drop all pending updates.                                           |
| `telegram-bot.spring.update-receiver.webhook.secret-token`                                    | -                  | A secret token to be sent in a header `X-Telegram-Bot-Api-Secret-Token`.             |
| `telegram-bot.spring.update-receiver.webhook.secret-token.random-generation`                  | `RANDOM_UUID`      | Autogeneration of secret token. Available `NONE`, `RANDOM_UUID`, `RANDOM_256_CHARS`. |
| `telegram-bot.spring.update-receiver.webhook.secret-token.random-generation.print-on-startup` | `false`            | Pass **True** to print generated token on application startup.                       |

More info about webhook in [official docs](https://core.telegram.org/bots/api#setwebhook).

## Custom receiver

And you can implement custom receiver (long polling or webhook, whatever you want).

```kotlin
class CustomUpdateReceiver(
    private val bot: TelegramBot,
    private val updateResolver: UpdateResolver,
    private val config: CustomConfig,
) : UpdateReceiver {

    private val logger: Logger = LoggerFactory.getLogger(CustomUpdateReceiver::class.java)

    override fun start(): Unit = runBlocking(Dispatchers.Default) {
        // Starting your bot
        // Calling bot.getUpdates() or bot.setWebhook()
        logger.info("Started custom update receiver.")
    }
    
    // Calling updateResolver.processUpdate()

    override fun stop(): Unit = runBlocking(Dispatchers.Default) {
        // Stopping your bot
        // Calling bot.deleteWebhook() or stopping client with long polling
        logger.info("Stopped custom update receiver.")
    }
}
```
```kotlin
val config = TelegramBotConfig().apply {
    // other settings

    receiving {
        updateReceiver = { CustomUpdateReceiver(telegramBot, receiving.updateResolver, CustomConfig()) }
    }
}
```

You can find examples in [LongPollingUpdateReceiver](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/telegram-bot-core/src/main/kotlin/io/github/dehuckakpyt/telegrambot/receiver/LongPollingUpdateReceiver.kt) and [WebhookUpdateReceiver](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/telegram-bot-spring/src/main/kotlin/io/github/dehuckakpyt/telegrambot/receiver/WebhookUpdateReceiver.kt).
