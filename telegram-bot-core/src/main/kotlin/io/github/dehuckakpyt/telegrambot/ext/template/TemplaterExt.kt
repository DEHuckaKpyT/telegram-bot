package io.github.dehuckakpyt.telegrambot.ext.template

import io.github.dehuckakpyt.telegrambot.template.RegexTemplater
import io.github.dehuckakpyt.telegrambot.template.Templater


/**
 * Created on 27.07.2024.
 *
 * @author Denis Matytsin
 */
val Templater.Companion.regex get() = Templater.Companion.regex()

fun Templater.Companion.regex(
    replaceRegex: Regex = Regex("(\\$\\{([a-zA-Z]+[a-zA-Z0-9_]*)\\})"),
    replaceNullWith: String = "",
): Templater = RegexTemplater(replaceRegex, replaceNullWith)