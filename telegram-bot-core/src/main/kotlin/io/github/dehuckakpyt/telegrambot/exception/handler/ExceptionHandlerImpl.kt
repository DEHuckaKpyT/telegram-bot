package io.github.dehuckakpyt.telegrambot.exception.handler

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.exception.chat.PrivateChatException
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
     * @param chatId in which chat may be thrown exception
     * @param block handler action for invoke
     */
    override suspend fun execute(chatId: Long, block: suspend () -> Unit): Unit {
        try {
            block()
        } catch (throwable: Throwable) {
            caught(chatId, throwable)
        }
    }

    /**
     * Execute handler action with exceptions handling.
     *
     * @param chatId in which chat was thrown exception
     * @param ex exception info
     */
    open suspend fun caught(chatId: Long, ex: Throwable): Unit {
        when (ex) {
            is PrivateChatException -> {
                if (chatId < 0) return // у личных чатов положительный id
                bot.sendMessage(chatId, template.whenKnownException with ("message" to ex.localizedMessage))
            }

            is ChatException -> bot.sendMessage(chatId,
                template.whenKnownException with ("message" to ex.localizedMessage))

            else -> {
                logger.error("Unexpected error while handling message in chat $chatId", ex)
                bot.sendMessage(chatId, template.whenUnknownException)
            }
        }
    }
}