package io.github.dehuckakpyt.telegrambot.data


/**
 * Created on 21.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
data class CommandInput(
    val command: String,
    val pathParam: String?,
    val lineParam: String?
)
