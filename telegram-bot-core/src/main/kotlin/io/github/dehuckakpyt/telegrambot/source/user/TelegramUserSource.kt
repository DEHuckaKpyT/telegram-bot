package io.github.dehuckakpyt.telegrambot.source.user

import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import io.github.dehuckakpyt.telegrambot.model.telegram.User


/**
 * Source for saving users in telegram-bot-core.
 *
 * @author Denis Matytsin
 */
public interface TelegramUserSource<EntityT : TelegramUser<out Any>> {

    /**
     * Create or update telegram user.
     *
     * Invokes on every `/start` command from user when added and every [io.github.dehuckakpyt.telegrambot.model.telegram.Update.myChatMember] when blocked.
     *
     * @param user info about user
     * @param available new user status (available now or not)
     */
    public suspend fun save(user: User, available: Boolean = true): Unit

    companion object
}