package io.github.dehuckakpyt.telegrambotexample.handling.update

import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import org.slf4j.LoggerFactory


/**
 * Created on 01.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
fun BotUpdateHandling.onSomeEvent() {
    val logger = LoggerFactory.getLogger("io.github.dehuckakpyt.telegrambotexample.event.BotEventHandling.onSomeEvent")
    val template = "some \${arg}"

    message {
        logger.info("Received message text: $text")
    }

    inlineQuery {
        logger.info("Received query: $query")

        bot.answerInlineQuery(inlineQueryId = id, results = listOf())
    }

    preCheckoutQuery {
        bot.answerPreCheckoutQuery(preCheckoutQueryId = id, ok = true)
        bot.sendMessage(123L, template with ("arg" to 321))

        next(userId = from.id, step = "test")
    }
}