package io.github.dehuckakpyt.telegrambot.resolver

import com.dehucka.microservice.logger.Logging
import com.elbekd.bot.types.CallbackQuery
import com.elbekd.bot.types.Message
import com.elbekd.bot.types.Update
import com.elbekd.bot.types.UpdateMessage
import io.github.dehuckakpyt.telegrambot.argument.CallbackArgument
import io.github.dehuckakpyt.telegrambot.argument.factory.MessageContainerFactory
import io.github.dehuckakpyt.telegrambot.argument.message.*
import io.github.dehuckakpyt.telegrambot.context.InternalKoinComponent
import io.github.dehuckakpyt.telegrambot.context.getInternal
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.ext.chatId
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.template.Templating
import org.koin.core.component.get


/**
 * Created on 12.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class UpdateResolver : InternalKoinComponent, Templating, Logging {

    private val callbackSerializer = get<CallbackSerializer>()
    private val chainSource = get<ChainSource>()
    private val messageSource = get<MessageSource>()
    private val chainResolver = getInternal<ChainResolver>()
    private val exceptionHandler = getInternal<ExceptionHandler>()

    suspend fun processUpdate(update: Update): Unit {
        if (update !is UpdateMessage) return

        val message = update.message
        val from = message.from
        val text = message.text
        val chatId = message.chatId

        if (from == null) {
            logger.warn("Don't expect message without fromId.\nchatId = '$chatId'\ntext = $text")
            return
        }

        messageSource.save(chatId, from.id, message.messageId, text)

        exceptionHandler.execute(chatId) {
            CommandArgument.fetchCommand(text)?.let {
                processCommand(it, message)
            } ?: processMessage(message)
        }
    }

    private suspend fun processCommand(command: String, message: Message): Unit = with(message) {
        chainResolver.getCommand(command)
            .invoke(CommandArgument(chatId, message))
    }

    private suspend fun processMessage(message: Message): Unit = with(message) {
        val chain = chainSource.get(chatId, from!!.id)
        val factory = message.containerFactory
        val action = chainResolver.getStep(chain?.step, factory.type)

        action.invoke(factory.create(chatId, message, chain!!.content))
    }

    suspend fun processCallback(callback: CallbackQuery): Unit = with(callback) {
        val data = data ?: return

        exceptionHandler.execute(chatId) {
            val (callbackName, callbackContent) = callbackSerializer.fromCallback(data)

            chainResolver.getCallback(callbackName)?.invoke(
                CallbackArgument(chatId, callback, callbackContent)
            )
        }
    }

    private val Message.containerFactory: MessageContainerFactory
        get() = when {
            TextMessageArgument.matches(this) -> TextMessageArgument.Companion
            AudioMessageArgument.matches(this) -> AudioMessageArgument.Companion
            VoiceMessageArgument.matches(this) -> VoiceMessageArgument.Companion
            ContactMessageArgument.matches(this) -> ContactMessageArgument.Companion
            DocumentMessageArgument.matches(this) -> DocumentMessageArgument.Companion
            PhotoMessageArgument.matches(this) -> PhotoMessageArgument.Companion
            else -> throw ChatException("Такой тип сообщения ещё не поддерживается.")
        }
}