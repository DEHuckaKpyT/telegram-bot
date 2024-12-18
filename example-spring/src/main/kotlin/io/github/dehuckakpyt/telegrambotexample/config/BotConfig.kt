package io.github.dehuckakpyt.telegrambotexample.config

import io.github.dehuckakpyt.telegrambot.annotation.EnableTelegramBot
import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.ext.config.receiver.handling
import io.github.dehuckakpyt.telegrambot.ext.dynamicFreeMarker
import io.github.dehuckakpyt.telegrambot.template.Templater
import io.github.dehuckakpyt.telegrambotexample.exception.CustomExceptionHandler
import io.github.dehuckakpyt.telegrambotexample.handler.buttonCommand
import io.github.dehuckakpyt.telegrambotexample.handler.exceptionCommand
import io.github.dehuckakpyt.telegrambotexample.handler.templateCommand
import io.github.dehuckakpyt.telegrambotexample.handler.withArgsCommand
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * Created on 29.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@EnableTelegramBot
@Configuration
class BotConfig {

    @Bean
    fun telegramBotConfig(): TelegramBotConfig = TelegramBotConfig().apply {
        templater = { Templater.dynamicFreeMarker }

        receiving {
            exceptionHandler = { CustomExceptionHandler(telegramBot, receiving.messageTemplate, templater) }

            handling {
                buttonCommand()
                templateCommand()
                exceptionCommand()
                withArgsCommand()
            }
        }
    }
}