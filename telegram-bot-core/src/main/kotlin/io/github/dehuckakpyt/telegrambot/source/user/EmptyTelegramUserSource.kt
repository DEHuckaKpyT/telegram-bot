package io.github.dehuckakpyt.telegrambot.source.user

import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import io.github.dehuckakpyt.telegrambot.model.telegram.User

/**
 * Implementation for ignoring saving users.
 *
 * @author Denis Matytsin
 */
internal class EmptyTelegramUserSource : TelegramUserSource<TelegramUser<out Any>> {
    override suspend fun save(user: User, available: Boolean) {}
}