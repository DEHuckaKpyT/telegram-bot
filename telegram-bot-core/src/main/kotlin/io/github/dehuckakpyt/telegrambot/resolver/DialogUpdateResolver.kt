package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.argument.CallbackArgument
import io.github.dehuckakpyt.telegrambot.argument.message.CommandArgument
import io.github.dehuckakpyt.telegrambot.argument.message.factory.MessageArgumentFactory
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.ext.chatId
import io.github.dehuckakpyt.telegrambot.model.type.CallbackQuery
import io.github.dehuckakpyt.telegrambot.model.type.Message
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import org.slf4j.LoggerFactory


/**
 * Created on 12.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class DialogUpdateResolver(
    private val callbackSerializer: CallbackSerializer,
    private val chainSource: ChainSource,
    private val chainResolver: ChainResolver,
    private val exceptionHandler: ExceptionHandler,
    private val messageArgumentFactories: List<MessageArgumentFactory>,
    private val messageSource: MessageSource,
    private val username: String,
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    suspend fun processMessage(message: Message): Unit {
        val from = message.from
        val text = message.text
        val chatId = message.chatId

        if (from == null) {
            logger.warn("Don't expect message without fromId.\nchatId = '$chatId'\ntext = $text")
            return
        }

        exceptionHandler.execute(chatId) {
            fetchCommand(text)?.let {
                processCommandMessage(it, message)
            } ?: processGeneralMessage(message)
        }
    }

    private suspend fun processCommandMessage(command: String, message: Message): Unit = with(message) {
        messageSource.save(chatId, from!!.id, messageId, type = "COMMAND", text = text)

        chainResolver.getCommand(command).invoke(CommandArgument(chatId, message))
    }

    private suspend fun processGeneralMessage(message: Message): Unit = with(message) {
        val chain = chainSource.get(chatId, from!!.id)
        val factory = message.messageArgumentFactory

        messageSource.save(chatId, from.id, messageId, factory.messageType, chain?.step, factory.getMessageText(message))

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

    private val Message.messageArgumentFactory: MessageArgumentFactory
        get() = messageArgumentFactories.firstOrNull { it.matches(this) }
            ?: throw ChatException("Такой тип сообщения ещё не поддерживается.")

    private fun fetchCommand(input: String?): String? {
        input ?: return null

        val find = commandRegex.find(input) ?: return null
        val groups = find.groups

        val command = groups[1]?.value ?: return null

        val usernameActual = groups[2]?.value
        if (usernameActual != null && usernameActual != username) return null

        return command
    }

    companion object {
        private val commandRegex = Regex("^(/[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*)(?:__[a-zA-Z0-9-_]+)?(?:@([a-zA-Z_]+))?")
    }
}