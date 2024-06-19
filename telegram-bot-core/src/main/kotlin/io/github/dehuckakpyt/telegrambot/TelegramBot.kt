package io.github.dehuckakpyt.telegrambot

import io.github.dehuckakpyt.telegrambot.api.TelegramBotApiExt
import io.github.dehuckakpyt.telegrambot.api.client.TelegramApiClient
import kotlin.String

/**
 * Created on 19.06.2024.
 *
 * @author KScript
 */
public interface TelegramBot : TelegramBotApiExt {
    public val username: String

    public val client: TelegramApiClient
}
