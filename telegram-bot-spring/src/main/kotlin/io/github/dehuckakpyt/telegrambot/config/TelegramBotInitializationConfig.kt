package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.BotHandler
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.context.SpringContext
import io.github.dehuckakpyt.telegrambot.context.TelegramBotContext
import io.github.dehuckakpyt.telegrambot.factory.TelegramBotFactory
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.template.Templater
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.EventListener
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.core.env.Environment


/**
 * Created on 29.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
class TelegramBotInitializationConfig(
    private val environment: Environment,
    private val applicationContext: ApplicationContext,
    telegramBotConfig: TelegramBotConfig?,
) {
    private final val logger = LoggerFactory.getLogger(javaClass)
    private final val botContext: TelegramBotContext

    init {
        SpringContext.context = applicationContext
    }

    init {
        val config: TelegramBotConfig = (telegramBotConfig ?: TelegramBotConfig()).apply {
            if (token == null) token = environment.getProperty("telegram-bot.token")
            if (username == null) username = environment.getProperty("telegram-bot.username")
        }

        botContext = TelegramBotFactory.createTelegramBotContext(config)
    }

    @Bean
    fun telegramBot(): TelegramBot = botContext.telegramBot

    @Bean
    fun templater(): Templater = botContext.templater

    @Bean(autowireCandidate = false)
    fun botHandling(): BotHandling = botContext.botHandling

    @EventListener(ApplicationReadyEvent::class)
    fun startTelegramBot() {
        // initialize all handlers
        applicationContext.getBeansOfType(BotHandler::class.java)

        // start receiving updates
        logger.info("Starting telegram-bot '${botContext.telegramBot.username}'..")
        botContext.updateReceiver.start()
        logger.info("Telegram-bot '${botContext.telegramBot.username}' started.")
    }

    @EventListener(ContextClosedEvent::class)
    fun stopTelegramBot() {
        logger.info("Stopping telegram-bot '${botContext.telegramBot.username}'..")
        botContext.updateReceiver.stop()
        logger.info("Telegram-bot '${botContext.telegramBot.username}' stopped.")
    }
}