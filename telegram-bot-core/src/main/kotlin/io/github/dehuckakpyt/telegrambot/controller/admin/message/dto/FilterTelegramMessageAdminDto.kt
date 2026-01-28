package io.github.dehuckakpyt.telegrambot.controller.admin.message.dto


/**
 * @author Denis Matytsin
 */
data class FilterTelegramMessageAdminDto(
    val chatIdsIn: Collection<Long>?,
    val fromIdsIn: Collection<Long>?,
)
