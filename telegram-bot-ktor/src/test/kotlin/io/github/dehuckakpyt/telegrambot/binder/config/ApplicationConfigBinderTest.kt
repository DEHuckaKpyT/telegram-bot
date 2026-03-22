package io.github.dehuckakpyt.telegrambot.binder.config

import io.github.dehuckakpyt.telegrambot.config.properties.TelegramBotProperties
import io.ktor.server.config.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ApplicationConfigBinderTest {

    @Test
    fun `bind telegram-bot subtree from ktor application config`() {
        val config = MapApplicationConfig(
            "telegram-bot.token" to "ktor-token",
            "telegram-bot.username" to "",
            "telegram-bot.receiving.long-polling.timeout" to "45",
            "telegram-bot.receiving.webhook.url-host" to "https://ktor.example.com",
        )

        val properties = ApplicationConfigBinder.bind(
            applicationConfig = config,
            rootPath = "telegram-bot",
            targetClass = TelegramBotProperties::class.java,
        )

        assertNotNull(properties)

        assertEquals("ktor-token", properties.token)
        assertNull(properties.username)
        assertEquals(45, properties.receiving?.longPolling?.timeout)
        assertEquals("https://ktor.example.com", properties.receiving?.webhook?.urlHost)
    }
}
