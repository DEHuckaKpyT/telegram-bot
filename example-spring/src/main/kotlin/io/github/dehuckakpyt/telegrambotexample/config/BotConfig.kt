package io.github.dehuckakpyt.telegrambotexample.config

import io.github.dehuckakpyt.telegrambot.annotation.EnableTelegramBot
import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambotexample.exception.CustomExceptionHandler
import io.github.dehuckakpyt.telegrambotexample.handler.*
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
        receiving {
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