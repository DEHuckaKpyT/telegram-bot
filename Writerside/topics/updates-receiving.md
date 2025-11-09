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

It available only for web frameworks (Spring and Ktor). *But don't get too upset. More details in <a href="updates-receiving.md#custom-receiver">"Custom receiver"</a>.

<tabs id="template-factory-receiving-templates" group="telegram-bot-code">
    <tab title="Spring" group-key="spring">
        <p>You can configure it easily:</p>
        <code-block lang="yaml">
            telegram-bot:
                spring:
                    update-receiver: webhook
                    # URL to your bot application
                    update-receiver.webhook.url.host: "https://my.domain.com/api/my-awesome-bot"
        </code-block>
        <p>
          By default, a unique random token will be generated for the <code>X-Telegram-Bot-Api-Secret-Token</code> header. 
          By default, the full URL will be: <code>https://my.domain.com/api/my-awesome-bot/updates/receive</code>
        </p>
        <p>All available properties:</p>
        <table>
          <thead>
            <tr>
              <th>PROPERTY</th>
              <th>DEFAULT</th>
              <th>DESCRIPTION</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><code>telegram-bot.spring.update-receiver</code></td>
              <td><code>long-polling</code></td>
              <td>Choosing <code>long polling</code> or <code>webhook</code></td>
            </tr>
            <tr>
              <td><code>telegram-bot.spring.update-receiver.webhook.url.host</code></td>
              <td>-</td>
              <td><b>Required</b>: Base URL of your application. Will be concatenated with <code>url.path</code>.</td>
            </tr>
            <tr>
              <td><code>telegram-bot.spring.update-receiver.webhook.url.path</code></td>
              <td><code>/updates/receive</code></td>
              <td>API endpoint path. Will be appended to host</td>
            </tr>
            <tr>
              <td><code>telegram-bot.spring.update-receiver.webhook.certificate.path</code></td>
              <td>-</td>
              <td>Path to certificate (e.g. <code>/cert.pem</code>)</td>
            </tr>
            <tr>
              <td><code>telegram-bot.spring.update-receiver.webhook.ip-address</code></td>
              <td>-</td>
              <td>Fixed IP instead of DNS-resolved</td>
            </tr>
            <tr>
              <td><code>telegram-bot.spring.update-receiver.webhook.max-connections</code></td>
              <td>-</td>
              <td>Maximum simultaneous HTTPS connections</td>
            </tr>
            <tr>
              <td><code>telegram-bot.spring.update-receiver.webhook.drop-pending-updates</code></td>
              <td>-</td>
              <td><b>true</b> = drop all unprocessed updates</td>
            </tr>
            <tr>
              <td><code>telegram-bot.spring.update-receiver.webhook.secret-token</code></td>
              <td>-</td>
              <td>Custom token for <code>X-Telegram-Bot-Api-Secret-Token</code> header</td>
            </tr>
            <tr>
              <td><code>telegram-bot.spring.update-receiver.webhook.secret-token.random-generation</code></td>
              <td><code>RANDOM_UUID</code></td>
              <td>Token generation: <code>NONE</code>, <code>RANDOM_UUID</code>, <code>RANDOM_256_CHARS</code></td>
            </tr>
            <tr>
              <td><code>telegram-bot.spring.update-receiver.webhook.secret-token.random-generation.print-on-startup</code></td>
              <td><code>false</code></td>
              <td><b>true</b> = print generated token on startup</td>
            </tr>
          </tbody>
        </table>
        <p>More details: 
          <a href="https://core.telegram.org/bots/api#setwebhook">Telegram official docs: setWebhook</a>
        </p>
    </tab>
    <tab title="Ktor + Koin" group-key="ktor">
        <p>You can configure it easily:</p>
        <code-block lang="kotlin">
            install(TelegramBot) {
                receiving {
                    webhook {
                        urlHost = "https://my.domain.com/api/my-awesome-bot"
                    }
                }
            }
        </code-block>
        <p>
          By default, a unique random token will be generated for the <code>X-Telegram-Bot-Api-Secret-Token</code> header. 
          By default, the full URL will be: <code>https://my.domain.com/api/my-awesome-bot/updates/receive</code>
        </p>
        <p>All available properties:</p>
        <table>
          <thead>
            <tr>
              <th>PROPERTY</th>
              <th>DEFAULT</th>
              <th>DESCRIPTION</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><code>urlHost</code></td>
              <td>-</td>
              <td><b>Required</b>: Base URL of your application. Will be concatenated with <code>url.path</code>.</td>
            </tr>
            <tr>
              <td><code>urlPath</code></td>
              <td><code>/updates/receive</code></td>
              <td>API endpoint path. Will be appended to host</td>
            </tr>
            <tr>
              <td><code>certificate</code></td>
              <td>-</td>
              <td>ContentInput file of certificate (e.g. path <code>/cert.pem</code>)</td>
            </tr>
            <tr>
              <td><code>ipAddress</code></td>
              <td>-</td>
              <td>Fixed IP instead of DNS-resolved</td>
            </tr>
            <tr>
              <td><code>maxConnections</code></td>
              <td>-</td>
              <td>Maximum simultaneous HTTPS connections</td>
            </tr>
            <tr>
              <td><code>dropPendingUpdates</code></td>
              <td>-</td>
              <td><b>true</b> = drop all unprocessed updates</td>
            </tr>
            <tr>
              <td><code>secretToken</code></td>
              <td>-</td>
              <td>Custom token for <code>X-Telegram-Bot-Api-Secret-Token</code> header</td>
            </tr>
            <tr>
              <td><code>secretTokenRandomGeneration</code></td>
              <td><code>RANDOM_UUID</code></td>
              <td>Token generation: <code>NONE</code>, <code>RANDOM_UUID</code>, <code>RANDOM_256_CHARS</code></td>
            </tr>
            <tr>
              <td><code>secretTokenRandomGenerationPrintOnStartup</code></td>
              <td><code>false</code></td>
              <td><b>true</b> = print generated token on startup</td>
            </tr>
          </tbody>
        </table>
        <p>More details: 
          <a href="https://core.telegram.org/bots/api#setwebhook">Telegram official docs: setWebhook</a>
        </p>
    </tab>
</tabs>

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
