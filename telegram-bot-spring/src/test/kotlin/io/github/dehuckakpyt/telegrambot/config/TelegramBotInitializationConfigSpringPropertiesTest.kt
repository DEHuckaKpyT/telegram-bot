package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.template.SpringMessageTemplate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.context.support.GenericApplicationContext
import org.springframework.core.env.MapPropertySource

class TelegramBotInitializationConfigSpringPropertiesTest {

    @Test
    fun `bind telegram bot params from spring environment properties`() {
        val applicationContext = GenericApplicationContext().apply {
            environment.propertySources.addFirst(
                MapPropertySource(
                    "test",
                    mapOf(
                        "telegram-bot.token" to "spring-props-token",
                        "telegram-bot.username" to "spring-props-bot",
                    ),
                )
            )
        }

        val initializationConfig = TelegramBotInitializationConfig(
            applicationContext = applicationContext,
            springMessageTemplate = SpringMessageTemplate(),
            callbackContentSourceExpression = null,
            chainSourceExpression = null,
            telegramMessageSource = null,
            telegramMessageSourceExpression = null,
            telegramUserSource = null,
            telegramUserSourceExpression = null,
            telegramChatSource = null,
            telegramChatSourceExpression = null,
            telegramChatStatusEventSource = null,
            telegramChatStatusEventSourceExpression = null,
            updateReceiverExpression = null,
            telegramBotConfig = null,
        )

        assertEquals("spring-props-bot", initializationConfig.telegramBot().username)
    }
}
