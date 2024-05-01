package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.container.CallbackContainer
import io.github.dehuckakpyt.telegrambot.container.Container
import io.github.dehuckakpyt.telegrambot.container.message.CommandContainer
import io.github.dehuckakpyt.telegrambot.container.message.factory.MessageContainerFactory
import io.github.dehuckakpyt.telegrambot.context.container.ContainerCoroutineContext
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import io.github.dehuckakpyt.telegrambot.ext.chatId
import io.github.dehuckakpyt.telegrambot.model.internal.AllowedUpdate
import io.github.dehuckakpyt.telegrambot.model.type.CallbackQuery
import io.github.dehuckakpyt.telegrambot.model.type.Message
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.withContext
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
    private val chainExceptionHandler: ChainExceptionHandler,
    private val messageArgumentFactories: List<MessageContainerFactory>,
    private val messageSource: MessageSource,
    private val username: String,
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    suspend fun processMessage(message: Message): Unit {
        val from = message.from
        val text = message.text
        val chat = message.chat

        if (from == null) {
            logger.warn("Don't expect message without fromId.\nchatId = '${chat.id}'\ntext = $text")
            return
        }

        exceptionHandler.execute(message.chat) {
            fetchCommand(text)?.let {
                processCommandMessage(it, message)
            } ?: processGeneralMessage(message)
        }
    }

    private suspend fun processCommandMessage(command: String, message: Message): Unit = with(message) {
        messageSource.save(
            chatId = chatId,
            fromId = from!!.id,
            fromBot = false,
            messageId = messageId,
            type = "COMMAND",
            step = command,
            stepContainerType = "COMMAND",
            text = text
        )

        chainResolver.getCommand(command).let { action ->
            invokeWithContainerContext(CommandContainer(message, step = command), action)
        }
    }

    private suspend fun processGeneralMessage(message: Message): Unit = with(message) {
        val chain = chainSource.get(chatId, from!!.id)
        val factory = message.messageContainerFactory

        messageSource.save(
            chatId = chatId,
            fromId = from.id,
            fromBot = false,
            messageId = messageId,
            type = factory.messageType,
            step = chain?.step,
            stepContainerType = factory.messageType,
            text = factory.getMessageText(message)
        )

        val action = chain?.step?.let { chainResolver.getStep(it, factory.type) }

        if (action == null) {
            if (chat.type == "private") chainExceptionHandler.whenStepNotFound() else return
        }

        invokeWithContainerContext(factory.create(message, chain.step, chain.content), action)
    }

    suspend fun processCallback(callback: CallbackQuery): Unit = with(callback) {
        val data = data ?: return
        if (message !is Message) return

        exceptionHandler.execute(message.chat) {
            val (callbackName, callbackContent) = callbackSerializer.fromCallback(data)

            chainResolver.getCallback(callbackName)?.let { action ->
                invokeWithContainerContext(CallbackContainer(callback, step = callbackName, callbackContent), action)
            }
        }
    }

    internal val allowedUpdates: Set<AllowedUpdate> get() = chainResolver.allowedUpdates

    private val Message.messageContainerFactory: MessageContainerFactory
        get() = messageArgumentFactories.firstOrNull { it.matches(this) }
            ?: throw RuntimeException("Not found factory for received argument type.")

    private fun fetchCommand(input: String?): String? {
        input ?: return null

        val find = commandRegex.find(input) ?: return null
        val groups = find.groups

        val command = groups[1]?.value ?: return null

        val usernameActual = groups[2]?.value
        if (usernameActual != null && usernameActual != username) return null

        return command
    }

    private suspend fun <T : Container> invokeWithContainerContext(container: T, action: suspend T.() -> Unit) =
        withContext(currentCoroutineContext() + ContainerCoroutineContext(container)) {
            action.invoke(container)
        }

    companion object {
        private val commandRegex = Regex("^(/[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*)(?:__[a-zA-Z0-9-_]+)?(?:@([a-zA-Z_]+))?")
    }
}