package io.github.dehuckakpyt.telegrambot.exception.handler

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.model.telegram.Chat
import io.github.dehuckakpyt.telegrambot.template.MessageTemplate
import io.github.dehuckakpyt.telegrambot.template.Templater
import org.slf4j.LoggerFactory


/**
 * Created on 23.11.2023.
 *
 * Handler for catching all exceptions in BotHandler actions.
 *
 * @author Denis Matytsin
 */
open class ExceptionHandlerImpl(
    protected val bot: TelegramBot,
    protected val template: MessageTemplate,
    templater: Templater,
) : ExceptionHandler, Templater by templater {

    private val logger = LoggerFactory.getLogger(javaClass)

    /**
     * Execute handler action with exceptions handling.
     *
     * @param chat in which chat may be thrown exception
     * @param block handler action for invoke
     */
    override suspend fun execute(chat: Chat, block: suspend () -> Unit): Unit {
        try {
            block()
        } catch (throwable: Throwable) {
            caught(chat, throwable)
        }
    }

    /**
     * Execute handler action with exceptions handling.
     *
     * @param chat in which chat was thrown exception
     * @param ex exception info
     */
    open suspend fun caught(chat: Chat, ex: Throwable): Unit {
        when (ex) {
            is ChatException -> {
                bot.sendMessage(chat.id, template.whenKnownException with ("message" to ex.localizedMessage))
            }

            else -> {
                logger.error("Unexpected error while handling message in chat ${chat.id}", ex)
                bot.sendMessage(chat.id, template.whenUnknownException)
            }
        }
    }
}