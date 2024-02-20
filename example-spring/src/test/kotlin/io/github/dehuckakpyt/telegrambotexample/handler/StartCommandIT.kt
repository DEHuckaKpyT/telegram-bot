package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.test.annotation.EnableTelegramBotTest
import io.github.dehuckakpyt.telegrambot.test.sendUpdate
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.resource.resourceAsString
import io.mockk.clearAllMocks
import io.mockk.coVerify
import org.springframework.boot.test.context.SpringBootTest


@EnableTelegramBotTest
@SpringBootTest
class StartCommandIT(
    private val bot: TelegramBot,
) : FreeSpec({

    beforeEach {
        clearAllMocks()
    }

    "start command" {
        // Act
        sendUpdate(resourceAsString("/json/handler/start/update.json"))

        // Assert
        coVerify(timeout = 3000) {
            bot.sendMessage(123, "Стартовая команда.")
            bot.sendMessage(123, "Привет, меня зовут mock_bot :-)")
        }
    }
})
