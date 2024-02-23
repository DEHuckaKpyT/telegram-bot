package io.github.dehuckakpyt.telegrambotexample.handler

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.core.api.dataset.ExpectedDataSet
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.test.annotation.EnableTelegramBotTest
import io.github.dehuckakpyt.telegrambot.test.sendUpdate
import io.kotest.matchers.resource.resourceAsString
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * Created on 20.02.2024.
 *
 *
 *
 * @author Denis Matytsin
 */
@SpringBootTest
@EnableTelegramBotTest
class RegistrationHandlerIT @Autowired constructor(
    private val bot: TelegramBot,
) {

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Test
    @DataSet("/datasets/handler/registration/command.json")
    @ExpectedDataSet("/datasets/handler/registration/command__expected.json")
    fun `register command`() = runTest {
        // Arrange
        coEvery { bot.sendMessage(1_001, any(), replyMarkup = any()) } returns mockk()

        // Act
        sendUpdate(resourceAsString("/json/handler/registration/command-update.json"))

        // Assert
        coVerify(timeout = 3000) {
            bot.sendMessage(1_001, "Для регистрации введите номер или поделитесь своим контактом.", replyMarkup = isNull(inverse = true))
        }
    }
}