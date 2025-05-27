package io.github.dehuckakpyt.telegrambotexample.config

import io.github.dehuckakpyt.telegrambot.annotation.EnableTelegramBot
import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.ext.config.receiver.client
import io.github.dehuckakpyt.telegrambot.ext.config.receiver.eventListening
import io.github.dehuckakpyt.telegrambot.ext.config.eventListening
import io.github.dehuckakpyt.telegrambot.ext.config.receiver.handling
import io.github.dehuckakpyt.telegrambot.ext.dynamicFreeMarker
import io.github.dehuckakpyt.telegrambot.ext.event.listening.after
import io.github.dehuckakpyt.telegrambot.template.Templater
import io.github.dehuckakpyt.telegrambotexample.exception.CustomExceptionHandler
import io.github.dehuckakpyt.telegrambotexample.handler.buttonCommand
import io.github.dehuckakpyt.telegrambotexample.handler.exceptionCommand
import io.github.dehuckakpyt.telegrambotexample.handler.templateCommand
import io.github.dehuckakpyt.telegrambotexample.handler.withArgsCommand
import io.ktor.client.plugins.*
import org.slf4j.LoggerFactory
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
    private val logger = LoggerFactory.getLogger(BotConfig::class.java)

    @Bean
    fun telegramBotConfig(): TelegramBotConfig = TelegramBotConfig().apply {
        templater = { Templater.dynamicFreeMarker }

        client {
            defaultRequest {
                url {
                    host = "api.telegram.org"
                }
            }
        }

        eventListening {
            after method "getUpdates" called { args ->
                val offset: Long? by args
                val limit: Int? by args
                val timeout: Int? by args
                val allowedUpdates: Iterable<String>? by args

                logger.info("Called \"getUpdates\" while long polling with offset = $offset, limit = $limit, timeout = $timeout, allowedUpdates = $allowedUpdates")
            }
            after method "setWebhook" called { args ->
                val url: String by args
                val ipAddress: String? by args
                val maxConnections: Int? by args
                val allowedUpdates: Iterable<String>? by args
                val dropPendingUpdates: Boolean? by args
                val secretToken: String? by args

                logger.info("Called \"setWebhook\" with\n    url: $url\n    ipAddress: $ipAddress\n    maxConnections: $maxConnections\n    allowedUpdates: $allowedUpdates\n    dropPendingUpdates: $dropPendingUpdates\n    secretToken: $secretToken")
            }
        }

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