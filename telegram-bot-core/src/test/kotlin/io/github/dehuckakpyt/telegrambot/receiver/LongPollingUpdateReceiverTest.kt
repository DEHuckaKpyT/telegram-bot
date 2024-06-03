package io.github.dehuckakpyt.telegrambot.receiver

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.receiver.LongPollingConfig
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import io.kotest.core.spec.style.FreeSpec
import io.mockk.*


/**
 * Created on 15.04.2024.
 *
 * @author Denis Matytsin
 */
class LongPollingUpdateReceiverTest : FreeSpec({

    val bot = mockk<TelegramBot>(relaxed = true, relaxUnitFun = true)
    val updateResolver = mockk<UpdateResolver>()
    val config = LongPollingConfig()
    val updateReceiver = LongPollingUpdateReceiver(bot, updateResolver, config)

    val exception = RuntimeException("something went wrong")

    beforeEach {
        clearAllMocks()

        every { updateResolver.allowedUpdates } returns setOf("message")
    }

    afterEach {
        updateReceiver.stop()
    }

    "errors handling" - {
        "when 'bot.getUpdates()' throws exception it will resume after N seconds" {
            // Arrange
            coEvery { bot.getUpdates(any(), any(), any(), any()) } throws exception

            // Act
            updateReceiver.start()

            // Assert
            coVerify(exactly = 2, timeout = 5500) {
                bot.getUpdates(any(), any(), any(), any())
            }
            coVerify(exactly = 0, timeout = 5500) {
                updateResolver.processUpdate(any())
            }
        }
    }
})