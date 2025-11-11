package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.expression.ConfigExpression
import io.github.dehuckakpyt.telegrambot.context.TelegramBotContext
import io.github.dehuckakpyt.telegrambot.factory.TelegramBotFactory
import io.github.dehuckakpyt.telegrambot.factory.input.InputFactory
import io.github.dehuckakpyt.telegrambot.factory.keyboard.button.ButtonFactory
import io.github.dehuckakpyt.telegrambot.handler.BotHandler
import io.github.dehuckakpyt.telegrambot.handler.BotUpdateHandler
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chat.TelegramChatSource
import io.github.dehuckakpyt.telegrambot.source.chat.event.TelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.source.user.TelegramUserSource
import io.github.dehuckakpyt.telegrambot.template.SpringMessageTemplate
import io.github.dehuckakpyt.telegrambot.template.Templater
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.registerBean


/**
 * Created on 29.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Configuration
@EnableConfigurationProperties(SpringMessageTemplate::class)
class TelegramBotInitializationConfig(
    private val applicationContext: GenericApplicationContext,
    springMessageTemplate: SpringMessageTemplate,
    callbackContentSourceExpression: ConfigExpression<CallbackContentSource>?,
    chainSourceExpression: ConfigExpression<ChainSource>?,
    telegramMessageSourceExpression: ConfigExpression<MessageSource>?,
    telegramUserSourceExpression: ConfigExpression<TelegramUserSource<out TelegramUser>>?,
    telegramUserSource: TelegramUserSource<out TelegramUser>?,
    telegramChatSourceExpression: ConfigExpression<TelegramChatSource>?,
    telegramChatStatusEventSourceExpression: ConfigExpression<TelegramChatStatusEventSource>?,
    updateReceiverExpression: ConfigExpression<UpdateReceiver>?,
    @Value("\${telegram-bot.token}") botToken: String?,
    @Value("\${telegram-bot.username}") botUsername: String?,
    telegramBotConfig: TelegramBotConfig?,
) : DisposableBean {
    private final val logger = LoggerFactory.getLogger(TelegramBotInitializationConfig::class.java)
    private final val botContext: TelegramBotContext

    init {
        //TODO clean up this code part
        val config: TelegramBotConfig = (telegramBotConfig ?: TelegramBotConfig()).apply {
            if (token == null) token = botToken
            if (username == null) username = botUsername

            if (receiving.messageTemplate == null) receiving.messageTemplate = { springMessageTemplate }

            if (receiving.telegramUserSource == null) {
                if (telegramUserSourceExpression != null) receiving.telegramUserSource = telegramUserSourceExpression::configure
                else if (telegramUserSource != null) receiving.telegramUserSource = { telegramUserSource }
            }

            if (messageSource == null && telegramMessageSourceExpression != null) messageSource = telegramMessageSourceExpression::configure
            if (receiving.chainSource == null && chainSourceExpression != null) receiving.chainSource = chainSourceExpression::configure
            if (receiving.callbackContentSource == null && callbackContentSourceExpression != null) receiving.callbackContentSource = callbackContentSourceExpression::configure
            if (receiving.telegramChatSource == null && telegramChatSourceExpression != null) receiving.telegramChatSource = telegramChatSourceExpression::configure
            if (receiving.telegramChatStatusEventSource == null && telegramChatStatusEventSourceExpression != null) receiving.telegramChatStatusEventSource = telegramChatStatusEventSourceExpression::configure
            if (receiving.updateReceiver == null && updateReceiverExpression != null) receiving.updateReceiver = updateReceiverExpression::configure
        }

        botContext = TelegramBotFactory.createTelegramBotContext(config)

        if (telegramUserSource == null) applicationContext.registerBean { botContext.telegramUserSource }
    }

    @Bean
    fun telegramBot(): TelegramBot = botContext.telegramBot

    @Bean
    fun templater(): Templater = botContext.templater

    @Bean
    fun buttonFactory(): ButtonFactory = botContext.buttonFactory

    @Bean
    fun inputFactory(): InputFactory = botContext.inputFactory

    @Bean
    fun telegramMessageSource(): MessageSource = botContext.messageSource

    @Bean
    fun chainSource(): ChainSource = botContext.chainSource

    @Bean
    fun callbackContentSource(): CallbackContentSource = botContext.callbackContentSource

    @Bean(autowireCandidate = false)
    fun botHandling(): BotHandling = botContext.botHandling

    @Bean(autowireCandidate = false)
    fun botUpdateHandling(): BotUpdateHandling = botContext.botUpdateHandling

    @EventListener(ApplicationReadyEvent::class)
    fun startTelegramBot() {
        // initialize all handlers
        val botHandling = applicationContext.getBean(BotHandling::class.java)
        applicationContext.getBeansOfType(BotHandler::class.java).forEach { (_, handler) -> handler.block(botHandling) }
        val botUpdateHandling = applicationContext.getBean(BotUpdateHandling::class.java)
        applicationContext.getBeansOfType(BotUpdateHandler::class.java).forEach { (_, updateHandler) -> updateHandler.block(botUpdateHandling) }

        // start receiving updates
        logger.info("Starting telegram-bot '${botContext.telegramBot.username}'..")
        botContext.updateReceiver.start()
        logger.info("Telegram-bot '${botContext.telegramBot.username}' started.")
    }

    override fun destroy() {
        logger.info("Stopping telegram-bot '${botContext.telegramBot.username}'..")
        botContext.updateReceiver.stop()
        logger.info("Telegram-bot '${botContext.telegramBot.username}' stopped.")
    }
}