package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.test.annotation.EnableTelegramBotTest
import io.github.dehuckakpyt.telegrambot.test.sendUpdate
import io.github.dehuckakpyt.telegrambotexample.test.EnablePostgresTestContainer
import io.github.dehuckakpyt.telegrambotexample.test.compareCurrentDataSetWith
import io.github.dehuckakpyt.telegrambotexample.test.createDataSet
import io.kotest.core.spec.style.FreeSpec
import io.mockk.clearAllMocks
import io.mockk.coVerify
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
@EnableTelegramBotTest
@EnablePostgresTestContainer
class StartCommandIT(
    private val bot: TelegramBot,
) : FreeSpec({

    beforeEach {
        clearAllMocks()
    }

    "start command" {
        // Arrange
        createDataSet("/datasets/handler/start/command.json")

        // Act
        sendUpdate(javaClass.getResource("/json/handler/start/update.json").readText())

        // Assert
        coVerify {
            bot.sendMessage(1_001, "Start command.")
            bot.sendMessage(1_001, "Hello! My name is mock_bot :-)")
        }
        compareCurrentDataSetWith("/datasets/handler/start/command__expected.json")
    }
})
