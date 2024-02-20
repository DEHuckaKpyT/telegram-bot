package io.github.dehuckakpyt.telegrambot.test.annotation

import io.github.dehuckakpyt.telegrambot.test.TelegramBotUpdateManager


/**
 * Created on 13.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
annotation class EnableTelegramBotTest {
    companion object {
        init {
            TelegramBotUpdateManager
        }
    }
}
