package io.github.dehuckakpyt.telegrambot.factory

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.TelegramBotImpl
import io.github.dehuckakpyt.telegrambot.argument.message.factory.*
import io.github.dehuckakpyt.telegrambot.config.TelegramBotActualConfigImpl
import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.config.receiver.LongPollingConfig
import io.github.dehuckakpyt.telegrambot.config.receiver.UpdateReceiverActualConfigImpl
import io.github.dehuckakpyt.telegrambot.config.receiver.UpdateReceiverConfig
import io.github.dehuckakpyt.telegrambot.config.template.TelegramBotTemplatingActualConfigImpl
import io.github.dehuckakpyt.telegrambot.config.template.TelegramBotTemplatingConfig
import io.github.dehuckakpyt.telegrambot.context.TelegramBotContext
import io.github.dehuckakpyt.telegrambot.context.TelegramBotContextImpl
import io.github.dehuckakpyt.telegrambot.converter.JsonContentConverter
import io.github.dehuckakpyt.telegrambot.converter.SimpleCallbackSerializer
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandlerImpl
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandlerImpl
import io.github.dehuckakpyt.telegrambot.ext.empty
import io.github.dehuckakpyt.telegrambot.factory.button.ButtonFactoryImpl
import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatterImpl
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.receiver.LongPollingUpdateReceiver
import io.github.dehuckakpyt.telegrambot.resolver.ChainResolver
import io.github.dehuckakpyt.telegrambot.resolver.DialogUpdateResolver
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import io.github.dehuckakpyt.telegrambot.source.callback.InMemoryCallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.InMemoryChainSource
import io.github.dehuckakpyt.telegrambot.source.message.EmptyMessageSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.template.MessageTemplate
import io.github.dehuckakpyt.telegrambot.template.TemplaterImpl


/**
 * Created on 23.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
object TelegramBotFactory {

    fun createTelegramBot(token: String, username: String, messageSource: MessageSource = MessageSource.empty): TelegramBot =
        TelegramBotImpl(token, username, messageSource)

    fun createTelegramBotContext(config: TelegramBotConfig): TelegramBotContext {
        val actual = TelegramBotActualConfigImpl()
        val context = TelegramBotContextImpl()

        actual.token = config.token ?: throw IllegalArgumentException("Telegram-bot TOKEN must not be empty!")
        actual.username = config.username ?: throw IllegalArgumentException("Telegram-bot USERNAME must not be empty!")
        actual.messageSource = config.messageSource?.invoke(actual) ?: EmptyMessageSource()
        actual.telegramBot = TelegramBotImpl(actual.token, actual.username, actual.messageSource)

        context.telegramBot = actual.telegramBot

        buildTemplating(config, actual, context)
        buildReceiving(config, actual, context)

        return context
    }

    private fun buildTemplating(config: TelegramBotConfig, actual: TelegramBotActualConfigImpl, context: TelegramBotContextImpl) = with(TelegramBotTemplatingConfig()) {
        config.templating.invoke(this)

        val actualTemplating = TelegramBotTemplatingActualConfigImpl()
        actual.templating = actualTemplating
        actualTemplating.htmlFormatter = htmlFormatter?.invoke(actual) ?: HtmlFormatterImpl()
        actualTemplating.templater = TemplaterImpl(freemarkerConfig, actualTemplating.htmlFormatter)

        context.templater = actualTemplating.templater
    }

    private fun buildReceiving(config: TelegramBotConfig, actual: TelegramBotActualConfigImpl, context: TelegramBotContextImpl) = with(UpdateReceiverConfig()) {
        config.receiving.invoke(this)

        val actualReceiving = UpdateReceiverActualConfigImpl()
        actual.receiving = actualReceiving
        actualReceiving.messageTemplate = messageTemplate?.invoke(actual) ?: MessageTemplate()
        actualReceiving.contentConverter = contentConverter?.invoke(actual) ?: JsonContentConverter()
        actualReceiving.callbackContentSource = callbackContentSource?.invoke(actual) ?: InMemoryCallbackContentSource()
        actualReceiving.chainSource = chainSource?.invoke(actual) ?: InMemoryChainSource()
        actualReceiving.callbackSerializer = callbackSerializer?.invoke(actual) ?: SimpleCallbackSerializer(actualReceiving.callbackContentSource, actualReceiving.contentConverter, callbackDataDelimiter)
        val buttonFactory = ButtonFactoryImpl(actualReceiving.callbackSerializer)
        actualReceiving.exceptionHandler = exceptionHandler?.invoke(actual) ?: ExceptionHandlerImpl(actual.telegramBot, actualReceiving.messageTemplate, actual.templating.templater)
        actualReceiving.chainExceptionHandler = chainExceptionHandler?.invoke(actual) ?: ChainExceptionHandlerImpl(actualReceiving.messageTemplate, actual.templating.templater)

        val chainResolver = ChainResolver(actualReceiving.callbackSerializer, actualReceiving.chainSource, actualReceiving.chainExceptionHandler, actualReceiving.contentConverter)
        val messageArgumentFactories: List<MessageArgumentFactory> = listOf(
            AudioMessageArgumentFactory(),
            ContactMessageArgumentFactory(),
            DocumentMessageArgumentFactory(),
            PhotoMessageArgumentFactory(),
            TextMessageArgumentFactory(),
            VoiceMessageArgumentFactory(),
        )
        val dialogUpdateResolver = DialogUpdateResolver(actualReceiving.callbackSerializer, actualReceiving.chainSource, chainResolver, actualReceiving.exceptionHandler, messageArgumentFactories, actual.messageSource, actual.telegramBot.username)
        val updateResolver = UpdateResolver(dialogUpdateResolver)

        val botHandling = BotHandling(actual.telegramBot, chainResolver, actualReceiving.contentConverter, actual.templating.templater, buttonFactory)
        handling.invoke(botHandling)

        context.botHandling = botHandling
        context.buttonFactory = buttonFactory
        context.updateReceiver = updateReceiver?.invoke(actual.telegramBot, updateResolver) ?: LongPollingUpdateReceiver(actual.telegramBot, updateResolver, LongPollingConfig())
    }
}