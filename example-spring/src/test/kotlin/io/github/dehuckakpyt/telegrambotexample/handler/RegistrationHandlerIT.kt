package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.factory.keyboard.removeKeyboard
import io.github.dehuckakpyt.telegrambot.test.annotation.EnableTelegramBotTest
import io.github.dehuckakpyt.telegrambot.test.sendUpdate
import io.github.dehuckakpyt.telegrambotexample.test.EnablePostgresTestContainer
import io.github.dehuckakpyt.telegrambotexample.test.clearDatabase
import io.github.dehuckakpyt.telegrambotexample.test.compareCurrentDataSetWith
import io.github.dehuckakpyt.telegrambotexample.test.createDataSet
import io.kotest.core.spec.style.FreeSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.springframework.boot.test.context.SpringBootTest

/**
 * Created on 20.02.2024.
 *
 * @author Denis Matytsin
 */
@SpringBootTest
@EnableTelegramBotTest
@EnablePostgresTestContainer
class RegistrationHandlerIT(
    private val bot: TelegramBot,
) : FreeSpec({

    beforeEach {
        clearAllMocks()
        clearDatabase()
    }

    "register command" {
        // Arrange
        createDataSet("/datasets/handler/registration/command.json")
        coEvery { bot.sendMessage(1_001, any(), replyMarkup = any()) } returns mockk()

        // Act
        sendUpdate(javaClass.getResource("/json/handler/registration/command-update.json").readText())

        // Assert
        coVerify {
            bot.sendMessage(1_001, "To register, enter a number or share your contact.", replyMarkup = isNull(inverse = true))
        }
        compareCurrentDataSetWith("/datasets/handler/registration/command__expected.json")
    }

    "get contact / type contact" {
        // Arrange
        createDataSet("/datasets/handler/registration/contact.json")
        coEvery { bot.sendMessage(1_001, any(), replyMarkup = any()) } returns mockk()

        // Act
        sendUpdate(javaClass.getResource("/json/handler/registration/contact-update.json").readText())

        // Assert
        coVerify {
            bot.sendMessage(1_001, "Den, you have successfully registered with +79991234567!", replyMarkup = removeKeyboard())
        }
        compareCurrentDataSetWith("/datasets/handler/registration/contact__expected.json")
    }

    "get contact / type text" {
        // Arrange
        createDataSet("/datasets/handler/registration/text.json")
        coEvery { bot.sendMessage(1_001, any(), replyMarkup = any()) } returns mockk()

        // Act
        sendUpdate(javaClass.getResource("/json/handler/registration/text-update.json").readText())

        // Assert
        coVerify {
            bot.sendMessage(1_001, "Write how to address you.", replyMarkup = removeKeyboard())
        }
        compareCurrentDataSetWith("/datasets/handler/registration/text__expected.json")
    }

    "get contact / type text / wrong phone format" {
        // Arrange
        createDataSet("/datasets/handler/registration/text-wrong-format.json")
        coEvery { bot.sendMessage(1_001, any(), replyMarkup = any()) } returns mockk()

        // Act
        sendUpdate(javaClass.getResource("/json/handler/registration/text-wrong-format-update.json").readText())

        // Assert
        coVerify {
            bot.sendMessage(1_001, "Incorrect phone number format.")
        }
        compareCurrentDataSetWith("/datasets/handler/registration/text-wrong-format__expected.json")
    }

    "get firstname" {
        // Arrange
        createDataSet("/datasets/handler/registration/get-firstname.json")
        coEvery { bot.sendMessage(1_001, any(), replyMarkup = any()) } returns mockk()

        // Act
        sendUpdate(javaClass.getResource("/json/handler/registration/get-firstname-update.json").readText())

        // Assert
        coVerify {
            bot.sendMessage(1_001, "Den, you have successfully registered with +79991234567!")
        }
        compareCurrentDataSetWith("/datasets/handler/registration/get-firstname__expected.json")
    }
})