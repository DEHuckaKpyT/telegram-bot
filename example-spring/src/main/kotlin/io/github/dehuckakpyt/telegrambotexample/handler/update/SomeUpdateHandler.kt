package io.github.dehuckakpyt.telegrambotexample.handler.update

import io.github.dehuckakpyt.telegrambot.annotation.HandlerComponent
import io.github.dehuckakpyt.telegrambot.handler.BotUpdateHandler
import org.slf4j.LoggerFactory


/**
 * Created on 04.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
@HandlerComponent
class SomeUpdateHandler : BotUpdateHandler({
    val logger = LoggerFactory.getLogger(javaClass)

    message {
        logger.info("Received message with text \"$text\"")
    }

    inlineQuery {
        logger.info("Received query: $query")

        bot.answerInlineQuery(inlineQueryId = id, results = listOf())
    }
})