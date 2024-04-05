package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.test.annotation.EnableTelegramBotTest
import io.github.dehuckakpyt.telegrambot.test.sendUpdateAsync
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.resource.resourceAsString
import io.mockk.clearAllMocks
import io.mockk.coVerify
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
@EnableTelegramBotTest
class StartCommandIT(
    private val bot: TelegramBot,
) : FreeSpec({

    beforeEach {
        clearAllMocks()
    }

    "start command" {
        // Act
        sendUpdateAsync(resourceAsString("/json/handler/start/update.json"))

        // Assert
        coVerify(timeout = 3000) {
            bot.sendMessage(123, "Start command.")
            bot.sendMessage(123, "Hello! My name is mock_bot :-)")
        }
    }
})
