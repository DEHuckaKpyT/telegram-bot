package io.github.dehuckakpyt.telegrambot.manager.access.admin

/**
 * Manager for access to admin-ui api.
 *
 * @author Denis Matytsin
 */
interface AdminUIAccessManager {

    /** Returns True when has access to all apis. */
    fun hasAccess(): Boolean = false

    /** Returns True when has access to apis with TelegramUser. */
    fun hasAccessToTelegramUser(): Boolean = hasAccess()

    /** Returns True when has access to api `/admin/telegram-users/{id}`. */
    fun hasAccessToTelegramUserGet(): Boolean = hasAccessToTelegramUser()

    /** Returns True when has access to api `/admin/telegram-users/slice`. */
    fun hasAccessToTelegramUsersSlice(): Boolean = hasAccessToTelegramUser()

    /** Returns True when has access to api `/admin/telegram-users/page`. */
    fun hasAccessToTelegramUsersPage(): Boolean = hasAccessToTelegramUser()

    /** Returns True when has access to api `/admin/telegram-users/count`. */
    fun hasAccessToTelegramUsersCount(): Boolean = hasAccessToTelegramUser()

    /** Returns True when has access to apis with TelegramMessage. */
    fun hasAccessToTelegramMessages(): Boolean = hasAccess()

    /** Returns True when has access to api `/admin/telegram-messages/{id}`. */
    fun hasAccessToTelegramMessagesGet(): Boolean = hasAccessToTelegramMessages()

    /** Returns True when has access to api `/admin/telegram-messages/slice`. */
    fun hasAccessToTelegramMessagesSlice(): Boolean = hasAccessToTelegramMessages()

    /** Returns True when has access to api `/admin/telegram-messages/page`. */
    fun hasAccessToTelegramMessagesPage(): Boolean = hasAccessToTelegramMessages()

    /** Returns True when has access to api `/admin/telegram-messages/count`. */
    fun hasAccessToTelegramMessagesCount(): Boolean = hasAccessToTelegramMessages()
}
