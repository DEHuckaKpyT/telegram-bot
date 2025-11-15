package io.github.dehuckakpyt.telegrambot.controller.admin.user.dto


/**
 * Filter parameters for TelegramUser.
 *
 * Parameters applying by 'and'.
 *
 * @author Denis Matytsin
 */
data class FilterTelegramUserAdminDto(
    val userIdsIn: Set<Long>?,
    val anyStringFieldContainsIgnoreCase: String?,
    val usernameContainsIgnoreCase: String?,
)
