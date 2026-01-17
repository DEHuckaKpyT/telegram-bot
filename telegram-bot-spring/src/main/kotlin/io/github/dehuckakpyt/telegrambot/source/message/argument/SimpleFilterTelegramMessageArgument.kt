package io.github.dehuckakpyt.telegrambot.source.message.argument


/**
 * Filter parameters for TelegramMessage.
 *
 * Parameters applying by 'and'.
 *
 * @author Denis Matytsin
 */
open class SimpleFilterTelegramMessageArgument {
    var chatIdsIn: Collection<Long>? = null
}
