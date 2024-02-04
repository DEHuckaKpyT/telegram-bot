# Update handling

If you need to handle more updates than are available in the [Message chains](message-chains.md), you can receive any
updates in easy way.

<tabs id="update-handling-example" group="telegram-bot-code">
    <tab title="Spring" group-key="spring">
        <code>com/example/myproject/handler/update/SomeUpdateHandler.kt</code>
        <code-block lang="kotlin">
            @HandlerComponent
            class SomeUpdateHandler : BotUpdateHandler({
                val logger = LoggerFactory.getLogger(javaClass)
                // for example handle any message
                message {
                    logger.info("Received message text: $text")
                }
                // for example handle any inline query
                inlineQuery {
                    logger.info("Received query: $query")
                    bot.answerInlineQuery(inlineQueryId = id, results = listOf())
                }
            })
        </code-block>
    </tab>
    <tab title="Ktor + Koin" group-key="ktor">
        <code>com/example/myproject/handler/update/SomeUpdateHandler.kt</code>
        <code-block lang="kotlin">
            @Factory
            class SomeUpdateHandler : BotUpdateHandler({
                val logger = LoggerFactory.getLogger(javaClass)
                // for example handle any message
                message {
                    logger.info("Received message text: $text")
                }
                // for example handle any inline query
                inlineQuery {
                    logger.info("Received query: $query")
                    bot.answerInlineQuery(inlineQueryId = id, results = listOf())
                }
            })
        </code-block>
    </tab>
    <tab title="Core" group-key="core">
        <code>com/example/myproject/handling/update/SomeUpdateHandler.kt</code>
        <code-block lang="kotlin">
            fun BotUpdateHandling.onSomeEvent() {
                val logger = LoggerFactory.getLogger("io.github.dehuckakpyt.telegrambotexample.handling.update.BotEventHandling.onSomeEvent")
                // for example handle any message
                message {
                    logger.info("Received message text: $text")
                }
                // for example handle any inline query
                inlineQuery {
                    logger.info("Received query: $query")
                    bot.answerInlineQuery(inlineQueryId = id, results = listOf())
                }
            }
        </code-block>
    </tab>
</tabs>

First BotUpdateHandling is invoked, then BotHandling is invoked.
You can set the next step in update if you really want to (use it carefully).
Also, you can use templating and button factory.

<tabs id="update-handling-next-step-example" group="telegram-bot-code">
    <tab title="Spring" group-key="spring">
        <code>com/example/myproject/handler/update/SomeUpdateHandler.kt</code>
        <code-block lang="kotlin">
            @HandlerComponent
            class SomeUpdateHandler : BotUpdateHandler({
                val template = "some \${arg}"
                preCheckoutQuery {
                    bot.answerPreCheckoutQuery(preCheckoutQueryId = id, ok = true)
                    bot.sendMessage(123L, template with ("arg" to 321))
                    // and you can set next step for specified user (or chat and user)
                    next(userId = from.id, step = "test")
                }
            })
        </code-block>
    </tab>
    <tab title="Ktor + Koin" group-key="ktor">
        <code>com/example/myproject/handler/update/SomeUpdateHandler.kt</code>
        <code-block lang="kotlin">
            @Factory
            class SomeUpdateHandler : BotUpdateHandler({
                val template = "some \${arg}"
                preCheckoutQuery {
                    bot.answerPreCheckoutQuery(preCheckoutQueryId = id, ok = true)
                    bot.sendMessage(123L, template with ("arg" to 321))
                    // and you can set next step for specified user (or chat and user)
                    next(userId = from.id, step = "test")
                }
            })
        </code-block>
    </tab>
    <tab title="Core" group-key="core">
        <code>com/example/myproject/handling/update/SomeUpdateHandler.kt</code>
        <code-block lang="kotlin">
            fun BotUpdateHandling.onSomeEvent() {
                val template = "some \${arg}"
                preCheckoutQuery {
                    bot.answerPreCheckoutQuery(preCheckoutQueryId = id, ok = true)
                    bot.sendMessage(123L, template with ("arg" to 321))
                    // and you can set next step for specified user (or chat and user)
                    next(userId = from.id, step = "test")
                }
            }
        </code-block>
    </tab>
</tabs>
