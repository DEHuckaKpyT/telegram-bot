package io.github.dehuckakpyt.telegrambot.template


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface Templating {
    infix fun String.with(pair: Pair<String, Any>): String

    fun String.with(vararg pairs: Pair<String, Any>): String

    infix fun String.with(instance: Any): String
}