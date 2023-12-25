package io.github.dehuckakpyt.telegrambot.factory

import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.TelegramBotImpl
import io.github.dehuckakpyt.telegrambot.argument.message.factory.*
import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.config.receiver.LongPollingConfig
import io.github.dehuckakpyt.telegrambot.config.receiver.UpdateReceiverConfig
import io.github.dehuckakpyt.telegrambot.converter.JsonContentConverter
import io.github.dehuckakpyt.telegrambot.converter.SimpleCallbackSerializer
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandlerImpl
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandlerImpl
import io.github.dehuckakpyt.telegrambot.ext.empty
import io.github.dehuckakpyt.telegrambot.factory.button.ButtonFactoryImpl
import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatterImpl
import io.github.dehuckakpyt.telegrambot.receiver.LongPollingUpdateReceiver
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.resolver.ChainResolver
import io.github.dehuckakpyt.telegrambot.resolver.DialogUpdateResolver
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import io.github.dehuckakpyt.telegrambot.source.callback.InMemoryCallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.InMemoryChainSource
import io.github.dehuckakpyt.telegrambot.source.message.EmptyMessageSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.template.MessageTemplate
import io.github.dehuckakpyt.telegrambot.template.TemplatingImpl


/**
 * Created on 23.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
object TelegramBotFactory {

    fun createTelegramBot(token: String, username: String, messageSource: MessageSource = MessageSource.empty): TelegramBot =
        TelegramBotImpl(token, username, messageSource)

    fun createTelegramBot(config: TelegramBotConfig): TelegramBot = config.buildBot()

    fun createUpdateReceiver(telegramBot: TelegramBot, config: TelegramBotConfig): UpdateReceiver {
        val receiverConfig = UpdateReceiverConfig()
        config.receiving.invoke(receiverConfig, telegramBot)

        return receiverConfig.buildUpdateReceiver(config, telegramBot)
    }

    private fun TelegramBotConfig.buildBot(): TelegramBot {
        token ?: throw IllegalArgumentException("Telegram-bot TOKEN must not be empty!")
        username ?: throw IllegalArgumentException("Telegram-bot USERNAME must not be empty!")
        messageSource ?: let { messageSource = EmptyMessageSource() }

        return TelegramBotImpl(token!!, username!!, messageSource!!)
    }

    private fun UpdateReceiverConfig.buildUpdateReceiver(config: TelegramBotConfig, telegramBot: TelegramBot): UpdateReceiver {
        val callbackContentSourceActual = callbackContentSource ?: InMemoryCallbackContentSource()
        val chainSourceActual = chainSource ?: InMemoryChainSource()
        val callbackDataDelimiterActual = callbackDataDelimiter ?: '|'
        val contentConverterActual = contentConverter ?: JsonContentConverter()
        val callbackSerializerActual = callbackSerializer ?: SimpleCallbackSerializer(callbackContentSourceActual, contentConverterActual, callbackDataDelimiterActual)
        val htmlFormatterActual = htmlFormatter ?: HtmlFormatterImpl()
        val templating = TemplatingImpl(freemarkerConfig, htmlFormatterActual)
        val buttonFactory = ButtonFactoryImpl(callbackSerializerActual)
        val messageTemplateActual = messageTemplate ?: MessageTemplate()
        val exceptionHandlerActual = exceptionHandler ?: ExceptionHandlerImpl(telegramBot, messageTemplateActual, templating)
        val chainExceptionHandlerActual = chainExceptionHandler ?: ChainExceptionHandlerImpl(messageTemplateActual, templating)

        val chainResolver = ChainResolver(callbackSerializerActual, chainSourceActual, chainExceptionHandlerActual, contentConverterActual)
        val messageArgumentFactories: List<MessageArgumentFactory> = listOf(
            AudioMessageArgumentFactory(),
            ContactMessageArgumentFactory(),
            DocumentMessageArgumentFactory(),
            PhotoMessageArgumentFactory(),
            TextMessageArgumentFactory(),
            VoiceMessageArgumentFactory(),
        )
        val dialogUpdateResolver = DialogUpdateResolver(callbackSerializerActual, chainSourceActual, chainResolver, exceptionHandlerActual, messageArgumentFactories, config.messageSource!!, telegramBot.username)
        val updateResolver = UpdateResolver(dialogUpdateResolver)

        val botHandling = BotHandling(telegramBot, chainResolver, contentConverterActual, templating, buttonFactory)
        handling.invoke(botHandling)

        return updateReceiver?.invoke(telegramBot, updateResolver) ?: LongPollingUpdateReceiver(telegramBot, updateResolver, LongPollingConfig())
    }
}