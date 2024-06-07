package io.github.dehuckakpyt.telegrambotexample.config

import io.github.dehuckakpyt.telegrambot.annotation.EnableTelegramBot
import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.config.inDatabase
import io.github.dehuckakpyt.telegrambot.ext.strategy.invocation.fullSync
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.strategy.invocation.HandlerInvocationStrategy
import io.github.dehuckakpyt.telegrambotexample.exception.CustomExceptionHandler
import io.github.dehuckakpyt.telegrambotexample.handler.*
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


/**
 * Created on 29.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@EnableTelegramBot
@EnableJpaRepositories(basePackages = ["io.github.dehuckakpyt.telegrambot.repository"])
@EntityScan(basePackages = ["io.github.dehuckakpyt.telegrambot.model"])
@Configuration
class BotConfig {

    @Bean
    fun telegramBotConfig(): TelegramBotConfig = TelegramBotConfig().apply {
        messageSource = { MessageSource.inDatabase }

        receiving {
            callbackContentSource = {
                CallbackContentSource.inDatabase(
                    maxCallbackContentsPerUser = 2
                )
            }
            chainSource = { ChainSource.inDatabase }
            invocationStrategy = { HandlerInvocationStrategy.fullSync }
            exceptionHandler = { CustomExceptionHandler(telegramBot, receiving.messageTemplate, templating.templater) }

            handling {
                startCommand()
                chainCommand()
                buttonCommand()
                templateCommand()
                exceptionCommand()
                withArgsCommand()
            }
        }
    }
}