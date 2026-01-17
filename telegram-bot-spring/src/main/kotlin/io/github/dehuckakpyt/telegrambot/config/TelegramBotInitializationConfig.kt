package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_ENABLED
import io.github.dehuckakpyt.telegrambot.config.expression.ConfigExpression
import io.github.dehuckakpyt.telegrambot.context.TelegramBotContext
import io.github.dehuckakpyt.telegrambot.factory.TelegramBotFactory
import io.github.dehuckakpyt.telegrambot.factory.input.InputFactory
import io.github.dehuckakpyt.telegrambot.factory.keyboard.button.ButtonFactory
import io.github.dehuckakpyt.telegrambot.handler.BotHandler
import io.github.dehuckakpyt.telegrambot.handler.BotUpdateHandler
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import io.github.dehuckakpyt.telegrambot.manager.chain.ChainManager
import io.github.dehuckakpyt.telegrambot.model.source.TelegramChat
import io.github.dehuckakpyt.telegrambot.model.source.TelegramChatStatusEvent
import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chat.TelegramChatSource
import io.github.dehuckakpyt.telegrambot.source.chat.event.TelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.source.message.TelegramMessageSource
import io.github.dehuckakpyt.telegrambot.source.user.TelegramUserSource
import io.github.dehuckakpyt.telegrambot.template.SpringMessageTemplate
import io.github.dehuckakpyt.telegrambot.template.Templater
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.SmartLifecycle
import org.springframework.context.annotation.Bean
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.registerBean


/**
 * @author Denis Matytsin
 */
@AutoConfiguration
@EnableConfigurationProperties(SpringMessageTemplate::class)
@ConditionalOnProperty(name = [TELEGRAM_BOT_ENABLED], havingValue = "true", matchIfMissing = true)
class TelegramBotInitializationConfig(
    private val applicationContext: GenericApplicationContext,
    springMessageTemplate: SpringMessageTemplate,
    callbackContentSourceExpression: ConfigExpression<CallbackContentSource>?,
    chainSourceExpression: ConfigExpression<ChainSource>?,
    telegramMessageSource: TelegramMessageSource<out TelegramMessage<out Any>>?,
    telegramMessageSourceExpression: ConfigExpression<TelegramMessageSource<out TelegramMessage<out Any>>>?,
    telegramUserSource: TelegramUserSource<out TelegramUser<out Any>>?,
    telegramUserSourceExpression: ConfigExpression<TelegramUserSource<out TelegramUser<out Any>>>?,
    telegramChatSource: TelegramChatSource<out TelegramChat<out Any>>?,
    telegramChatSourceExpression: ConfigExpression<TelegramChatSource<out TelegramChat<out Any>>>?,
    telegramChatStatusEventSource: TelegramChatStatusEventSource<out TelegramChatStatusEvent<out Any>>?,
    telegramChatStatusEventSourceExpression: ConfigExpression<TelegramChatStatusEventSource<out TelegramChatStatusEvent<out Any>>>?,
    updateReceiverExpression: ConfigExpression<UpdateReceiver>?,
    @Value("\${telegram-bot.token}") botToken: String?,
    @Value("\${telegram-bot.username}") botUsername: String?,
    telegramBotConfig: TelegramBotConfig?,
) : SmartLifecycle {
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

            if (this.telegramMessageSource == null) {
                if (telegramMessageSourceExpression != null) this.telegramMessageSource = telegramMessageSourceExpression::configure
                else if (telegramMessageSource != null) this.telegramMessageSource = { telegramMessageSource }
            }

            if (receiving.telegramChatStatusEventSource == null) {
                if (telegramChatStatusEventSourceExpression != null) this.receiving.telegramChatStatusEventSource = telegramChatStatusEventSourceExpression::configure
                else if (telegramChatStatusEventSource != null) this.receiving.telegramChatStatusEventSource = { telegramChatStatusEventSource }
            }

            if (receiving.telegramChatSource == null) {
                if (telegramChatSourceExpression != null) this.receiving.telegramChatSource = telegramChatSourceExpression::configure
                else if (telegramChatSource != null) this.receiving.telegramChatSource = { telegramChatSource }
            }

            if (receiving.chainSource == null && chainSourceExpression != null) receiving.chainSource = chainSourceExpression::configure
            if (receiving.callbackContentSource == null && callbackContentSourceExpression != null) receiving.callbackContentSource = callbackContentSourceExpression::configure
            if (receiving.updateReceiver == null && updateReceiverExpression != null) receiving.updateReceiver = updateReceiverExpression::configure
        }

        botContext = TelegramBotFactory.createTelegramBotContext(config)

        if (telegramUserSource == null) applicationContext.registerBean { botContext.telegramUserSource }
        if (telegramMessageSource == null) applicationContext.registerBean { botContext.telegramMessageSource }
        if (telegramChatStatusEventSource == null) applicationContext.registerBean { botContext.telegramChatStatusEventSource }
        if (telegramChatSource == null) applicationContext.registerBean { botContext.telegramChatSource }
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
    fun chainSource(): ChainSource = botContext.chainSource

    @Bean
    fun callbackContentSource(): CallbackContentSource = botContext.callbackContentSource

    @Bean
    fun chainManager(): ChainManager = botContext.chainManager

    @Bean(autowireCandidate = false)
    fun botHandling(): BotHandling = botContext.botHandling

    @Bean(autowireCandidate = false)
    fun botUpdateHandling(): BotUpdateHandling = botContext.botUpdateHandling

    private var running = false

    override fun start() {
        // initialize all handlers
        val botHandling = applicationContext.getBean(BotHandling::class.java)
        applicationContext.getBeansOfType(BotHandler::class.java).forEach { (_, handler) -> handler.block(botHandling) }
        val botUpdateHandling = applicationContext.getBean(BotUpdateHandling::class.java)
        applicationContext.getBeansOfType(BotUpdateHandler::class.java).forEach { (_, updateHandler) -> updateHandler.block(botUpdateHandling) }

        // start receiving updates
        logger.info("Starting telegram-bot '${botContext.telegramBot.username}'..")
        botContext.updateReceiver.start()
        running = true
        logger.info("Telegram-bot '${botContext.telegramBot.username}' started.")
    }

    override fun stop() {
        // stop receiving updates
        logger.info("Stopping telegram-bot '${botContext.telegramBot.username}'..")
        botContext.updateReceiver.stop()
        running = false
        logger.info("Telegram-bot '${botContext.telegramBot.username}' stopped.")
    }

    override fun isRunning(): Boolean = running
}