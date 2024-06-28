package io.github.dehuckakpyt.telegrambot.converter


/**
 * Created on 12.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
data class CallbackDataInfo(
    val next: String,
    val content: String? = null,
)
