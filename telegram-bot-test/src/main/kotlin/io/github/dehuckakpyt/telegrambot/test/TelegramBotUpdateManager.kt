package io.github.dehuckakpyt.telegrambot.test

import com.fasterxml.jackson.databind.json.JsonMapper
import io.github.dehuckakpyt.telegrambot.config.TelegramBotActualConfig
import io.github.dehuckakpyt.telegrambot.config.receiver.UpdateReceiverConfig
import io.github.dehuckakpyt.telegrambot.model.telegram.Update
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import io.github.dehuckakpyt.telegrambot.test.ext.anyConstructed
import io.github.dehuckakpyt.telegrambot.test.mock.MockTelegramBot
import io.github.dehuckakpyt.telegrambot.test.mock.MockUpdateReceiver
import io.mockk.every
import io.mockk.mockkConstructor
import io.mockk.spyk
import kotlinx.coroutines.channels.Channel


/**
 * Created on 13.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
internal object TelegramBotUpdateManager {
    internal val updatesChannel = Channel<List<Update>>()
    internal lateinit var updateResolver: UpdateResolver
    internal val objectMapper = Class.forName("io.github.dehuckakpyt.telegrambot.api.client.TelegramApiClient")
        .getDeclaredField("DEFAULT_MAPPER")
        .apply { isAccessible = true }
        .get(null) as JsonMapper

    init {
        val telegramBotActualConfigClass = Class.forName("io.github.dehuckakpyt.telegrambot.config.TelegramBotActualConfigImpl").kotlin
        mockkConstructor(telegramBotActualConfigClass)
        val mockTelegramBot = spyk(MockTelegramBot())
        every { anyConstructed(telegramBotActualConfigClass) getProperty "telegramBot" } returns mockTelegramBot

        mockkConstructor(UpdateReceiverConfig::class)
        val mockUpdateReceiver: (TelegramBotActualConfig.() -> UpdateReceiver) = {
            updateResolver = receiving.updateResolver
            MockUpdateReceiver(receiving.updateResolver)
        }
        every { anyConstructed<UpdateReceiverConfig>() getProperty "updateReceiver" } returns mockUpdateReceiver
    }
}
