package io.github.dehuckakpyt.telegrambot.factory

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.TelegramBotImpl
import io.github.dehuckakpyt.telegrambot.config.TelegramBotActualConfigImpl
import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.config.receiver.LongPollingConfig
import io.github.dehuckakpyt.telegrambot.config.receiver.TelegramBotReceiverActualConfigImpl
import io.github.dehuckakpyt.telegrambot.container.message.factory.*
import io.github.dehuckakpyt.telegrambot.context.TelegramBotContext
import io.github.dehuckakpyt.telegrambot.context.TelegramBotContextImpl
import io.github.dehuckakpyt.telegrambot.converter.JsonContentConverter
import io.github.dehuckakpyt.telegrambot.converter.SimpleCallbackSerializer
import io.github.dehuckakpyt.telegrambot.event.listening.TelegramBotEventListening
import io.github.dehuckakpyt.telegrambot.event.managing.TelegramBotEventManager
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandlerImpl
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandlerImpl
import io.github.dehuckakpyt.telegrambot.factory.input.InputFactoryImpl
import io.github.dehuckakpyt.telegrambot.factory.keyboard.button.ButtonFactoryImpl
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import io.github.dehuckakpyt.telegrambot.receiver.LongPollingUpdateReceiver
import io.github.dehuckakpyt.telegrambot.resolver.ChainResolver
import io.github.dehuckakpyt.telegrambot.resolver.DialogUpdateResolver
import io.github.dehuckakpyt.telegrambot.resolver.EventUpdateResolver
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolverImpl
import io.github.dehuckakpyt.telegrambot.source.callback.InMemoryCallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.InMemoryChainSource
import io.github.dehuckakpyt.telegrambot.source.chat.EmptyTelegramChatSource
import io.github.dehuckakpyt.telegrambot.source.chat.event.EmptyTelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.source.message.EmptyTelegramMessageSource
import io.github.dehuckakpyt.telegrambot.source.user.EmptyTelegramUserSource
import io.github.dehuckakpyt.telegrambot.template.MessageTemplate
import io.github.dehuckakpyt.telegrambot.template.RegexTemplater
import io.ktor.client.*
import io.ktor.client.engine.apache.*


/**
 * Created on 23.11.2023.
 *
 * Factory for creating telegram bot instances and contexts.
 *
 * @author Denis Matytsin
 */
object TelegramBotFactory {

    /**
     * Create telegram bot instance only.
     *
     * You can create instance for making requests only. Without anything context. Requests only.
     *
     * @param token telegram bot token
     * @param username telegram bot username
     *
     * @return telegram bot instance for making requests
     */
    fun createTelegramBot(token: String, username: String, clientConfiguration: (HttpClientConfig<ApacheEngineConfig>.() -> Unit)? = null): TelegramBot =
        TelegramBotImpl(token, username, clientConfiguration, TelegramBotEventManager())

    /**
     * Create telegram bot with related isolated context.
     *
     * Isolated means that you can create few contexts and use them independently of each other.
     *
     * @param config configuration for context instance
     *
     * @return telegram bot context
     */
    fun createTelegramBotContext(config: TelegramBotConfig): TelegramBotContext {
        val actual = TelegramBotActualConfigImpl()
        val context = TelegramBotContextImpl()

        actual.token = config.token ?: throw IllegalArgumentException("Telegram-bot TOKEN must not be empty!")
        actual.username = config.username
        actual.clientConfiguration = config.clientConfiguration
        actual.telegramMessageSource = config.telegramMessageSource?.invoke(actual) ?: EmptyTelegramMessageSource()
        val telegramBotEventManager = TelegramBotEventManager()
        actual.telegramBot = TelegramBotImpl(actual.token, actual.username, actual.clientConfiguration, telegramBotEventManager)
        actual.templater = config.templater?.invoke(actual) ?: RegexTemplater()

        val telegramBotEventListening = TelegramBotEventListening(telegramBotEventManager, actual.telegramMessageSource, config.eventListeningPreventDefaults)
        config.eventListening?.invoke(telegramBotEventListening)

        context.telegramMessageSource = actual.telegramMessageSource
        context.telegramBot = actual.telegramBot
        context.templater = actual.templater
        context.telegramBotEventListening = telegramBotEventListening

        buildReceiving(config, actual, context)

        return context
    }

    private fun buildReceiving(config: TelegramBotConfig, actual: TelegramBotActualConfigImpl, context: TelegramBotContextImpl) = with(config.receiving) {
        val actualReceiving = TelegramBotReceiverActualConfigImpl()
        actual.receiving = actualReceiving
        actualReceiving.messageTemplate = messageTemplate?.invoke(actual) ?: MessageTemplate()
        actualReceiving.contentConverter = contentConverter?.invoke(actual) ?: JsonContentConverter()
        actualReceiving.callbackContentSource = callbackContentSource?.invoke(actual) ?: InMemoryCallbackContentSource()
        actualReceiving.chainSource = chainSource?.invoke(actual) ?: InMemoryChainSource()
        actualReceiving.telegramUserSource = telegramUserSource?.invoke(actual) ?: EmptyTelegramUserSource()
        actualReceiving.telegramChatSource = telegramChatSource?.invoke(actual) ?: EmptyTelegramChatSource()
        actualReceiving.telegramChatStatusEventSource = telegramChatStatusEventSource?.invoke(actual) ?: EmptyTelegramChatStatusEventSource()
        actualReceiving.callbackSerializer = callbackSerializer?.invoke(actual) ?: SimpleCallbackSerializer(actualReceiving.callbackContentSource, actualReceiving.contentConverter)
        val buttonFactory = ButtonFactoryImpl(actualReceiving.callbackSerializer)
        actualReceiving.exceptionHandler = exceptionHandler?.invoke(actual) ?: ExceptionHandlerImpl(actual.telegramBot, actualReceiving.messageTemplate, actual.templater)
        actualReceiving.chainExceptionHandler = chainExceptionHandler?.invoke(actual) ?: ChainExceptionHandlerImpl(actualReceiving.messageTemplate, actual.templater)

        val chainResolver = ChainResolver(actualReceiving.callbackSerializer, actualReceiving.chainSource, actualReceiving.chainExceptionHandler, actualReceiving.contentConverter)
        val messageArgumentFactories: List<MessageContainerFactory> = listOf(
            TextMessageContainerFactory(),
            AudioMessageContainerFactory(),
            DocumentMessageContainerFactory(),
            PhotoMessageContainerFactory(),
            StickerMessageContainerFactory(),
            VideoMessageContainerFactory(),
            VideoNoteMessageContainerFactory(),
            VoiceMessageContainerFactory(),
            ContactMessageContainerFactory(),
            LocationMessageContainerFactory(),
            WebAppDataMessageContainerFactory(),
            LeftChatMemberMessageContainerFactory(),
            NewChatMembersMessageContainerFactory(),
        )
        val dialogUpdateResolver = DialogUpdateResolver(actualReceiving.callbackSerializer, actualReceiving.chainSource, chainResolver, actualReceiving.exceptionHandler, actualReceiving.chainExceptionHandler, messageArgumentFactories, actual.telegramMessageSource, actual.receiving.telegramUserSource, actual.receiving.telegramChatSource, actual.receiving.telegramChatStatusEventSource, actual.telegramBot.username)
        val eventUpdateResolver = EventUpdateResolver()

        val botHandling = BotHandling(actual.telegramBot, chainResolver, actualReceiving.contentConverter, actual.templater, buttonFactory)
        val botUpdateHandling = BotUpdateHandling(actual.telegramBot, eventUpdateResolver, actualReceiving.chainSource, actual.templater, buttonFactory)
        val updateResolver = UpdateResolverImpl(dialogUpdateResolver, eventUpdateResolver)
        actualReceiving.updateResolver = updateResolver

        handling.invoke(botHandling)
        updateHandling.invoke(botUpdateHandling)

        context.telegramUserSource = actualReceiving.telegramUserSource
        context.telegramChatStatusEventSource = actualReceiving.telegramChatStatusEventSource
        context.callbackContentSource = actualReceiving.callbackContentSource
        context.chainSource = actualReceiving.chainSource
        context.botHandling = botHandling
        context.botUpdateHandling = botUpdateHandling
        context.buttonFactory = buttonFactory
        context.inputFactory = InputFactoryImpl()
        context.updateReceiver = updateReceiver?.invoke(actual) ?: LongPollingUpdateReceiver(actual.telegramBot, updateResolver, LongPollingConfig())
    }
}