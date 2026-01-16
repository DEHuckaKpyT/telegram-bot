package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.container.CallbackContainer
import io.github.dehuckakpyt.telegrambot.container.Container
import io.github.dehuckakpyt.telegrambot.container.message.CommandContainer
import io.github.dehuckakpyt.telegrambot.container.message.factory.MessageContainerFactory
import io.github.dehuckakpyt.telegrambot.context.container.ContainerCoroutineContext
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import io.github.dehuckakpyt.telegrambot.ext.source.chat.save
import io.github.dehuckakpyt.telegrambot.ext.update.message.chatId
import io.github.dehuckakpyt.telegrambot.ext.update.message.fetchCommand
import io.github.dehuckakpyt.telegrambot.model.source.TelegramChat
import io.github.dehuckakpyt.telegrambot.model.source.TelegramChatStatusEvent
import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import io.github.dehuckakpyt.telegrambot.model.telegram.CallbackQuery
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatMemberUpdated
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chat.EmptyTelegramChatSource
import io.github.dehuckakpyt.telegrambot.source.chat.TelegramChatSource
import io.github.dehuckakpyt.telegrambot.source.chat.event.EmptyTelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.source.chat.event.TelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.source.message.TelegramMessageSource
import io.github.dehuckakpyt.telegrambot.source.user.EmptyTelegramUserSource
import io.github.dehuckakpyt.telegrambot.source.user.TelegramUserSource
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
    private val telegramMessageSource: TelegramMessageSource<out TelegramMessage<out Any>>,
    private val telegramUserSource: TelegramUserSource<out TelegramUser<out Any>>,
    private val telegramChatSource: TelegramChatSource<out TelegramChat<out Any>>,
    private val telegramChatStatusEventSource: TelegramChatStatusEventSource<out TelegramChatStatusEvent<out Any>>,
    private val username: String,
) {
    private val logger = LoggerFactory.getLogger(DialogUpdateResolver::class.java)

    suspend fun processMessage(message: Message): Unit {
        val from = message.from
        val text = message.text
        val chat = message.chat

        if (from == null) {
            logger.warn("Don't expect message without fromId.\nchatId = '${chat.id}'\ntext = $text")
            return
        }

        val command = fetchCommand(text, username)
        if (command != null)
            exceptionHandler.executeCommand(message) {
                processCommand(command, message)
            }
        else
            exceptionHandler.executeStep(message) {
                processStep(message)
            }
    }

    private suspend fun processCommand(command: String, message: Message): Unit = with(message) {
        telegramMessageSource.save(
            message = message,
            fromBot = false,
            type = "COMMAND",
            step = command,
            stepContainerType = "COMMAND",
            text = text,
        )
        if (command == "/start") telegramUserSource.save(message.from!!, available = true)

        chainResolver.getCommand(command).let { action ->
            invokeWithContainerContext(CommandContainer(message, step = command), action)
        }
    }

    private suspend fun processStep(message: Message): Unit = with(message) {
        val chain = chainSource.get(chatId, from!!.id)
        val factory = message.messageContainerFactory

        if (factory == null) {
            logger.warn("Received message of unknown type. You can see 'io.github.dehuckakpyt.telegrambot.container.message.MessageType' for available message types. If you need new type, please create issue on github https://github.com/DEHuckaKpyT/telegram-bot/issues\nReceived message: $message.")
            return@with
        }

        telegramMessageSource.save(
            message = message,
            fromBot = false,
            type = factory.messageType,
            step = chain?.step,
            stepContainerType = factory.messageType,
            text = factory.getMessageText(message),
            fileIds = factory.getMessageFileIds(message),
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

        exceptionHandler.executeCallback(callback) {
            val (callbackName, callbackContent) = callbackSerializer.fromCallback(data)

            chainResolver.getCallback(callbackName)?.let { action ->
                invokeWithContainerContext(CallbackContainer(callback, step = callbackName, callbackContent), action)
            }
        }
    }

    suspend fun processMyChatMember(myChatMember: ChatMemberUpdated): Unit {
        if (myChatMember.chat.type == "private") {
            // status "member" skipping because of it saving when received command "/start"
            if (myChatMember.newChatMember.status == "kicked") telegramUserSource.save(myChatMember.from, available = false)
        } else {
            telegramChatSource.save(myChatMember)
        }

        telegramChatStatusEventSource.save(myChatMember)
    }

    internal val allowedUpdates: Set<String>
        get() = buildSet {
            addAll(chainResolver.allowedUpdates)

            // If at least one implementation is not "Empty", needs to receive "my_chat_member" update
            if (telegramUserSource !is EmptyTelegramUserSource || telegramChatSource !is EmptyTelegramChatSource || telegramChatStatusEventSource !is EmptyTelegramChatStatusEventSource) {
                add("my_chat_member")
            }
        }

    private val Message.messageContainerFactory: MessageContainerFactory?
        get() = messageArgumentFactories.firstOrNull { it.matches(this) }


    private suspend fun <T : Container> invokeWithContainerContext(container: T, action: suspend T.() -> Unit) =
        withContext(ContainerCoroutineContext(container)) {
            action.invoke(container)
        }
}