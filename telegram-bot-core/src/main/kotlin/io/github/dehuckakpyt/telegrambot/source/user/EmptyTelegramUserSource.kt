package io.github.dehuckakpyt.telegrambot.source.user

import io.github.dehuckakpyt.telegrambot.model.telegram.User

/**
 * Implementation for ignoring saving users.
 *
 * @author Denis Matytsin
 */
internal class EmptyTelegramUserSource : TelegramUserSource {
    override suspend fun save(user: User, available: Boolean) {}
}