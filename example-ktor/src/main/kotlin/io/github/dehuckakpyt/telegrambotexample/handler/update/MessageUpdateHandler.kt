package io.github.dehuckakpyt.telegrambotexample.handler.update

import io.github.dehuckakpyt.telegrambot.handler.BotUpdateHandler
import org.koin.core.annotation.Factory
import org.slf4j.LoggerFactory


/**
 * Created on 04.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
@Factory
class MessageUpdateHandler : BotUpdateHandler({
    val logger = LoggerFactory.getLogger(javaClass)

    message {
        logger.info("Received message with text \"$text\"")
    }
})