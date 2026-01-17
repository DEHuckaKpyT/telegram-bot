package io.github.dehuckakpyt.telegrambot.source.user.argument


/**
 * Filter parameters for TelegramUser.
 *
 * Parameters applying by 'and'.
 *
 * @author Denis Matytsin
 */
open class SimpleFilterTelegramUserArgument {
    var userIdsIn: Collection<Long>? = null
    var anyStringFieldContainsIgnoreCase: String? = null
    var usernameContainsIgnoreCase: String? = null
}
