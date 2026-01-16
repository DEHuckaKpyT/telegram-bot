package io.github.dehuckakpyt.telegrambot.source.message.argument


/**
 * @author Denis Matytsin
 */
data class FilterTelegramMessageArgument(
    var chatIdsIn: Collection<Long>? = null,
)
