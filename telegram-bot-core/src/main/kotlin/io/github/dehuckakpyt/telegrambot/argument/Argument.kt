package io.github.dehuckakpyt.telegrambot.argument

import io.github.dehuckakpyt.telegrambot.model.type.User


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class Argument(
    val chatId: Long,
    internal val content: String?
) {
    abstract val from: User

    internal var nextStep: String? = null
    internal var nextStepInstance: Any? = null
}