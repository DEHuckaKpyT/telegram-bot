package io.github.dehuckakpyt.telegrambot.template


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface Templater {

    infix fun String.with(pair: Pair<String, Any>): String = with(mutableMapOf(pair))

    fun String.with(vararg pairs: Pair<String, Any>): String = with(mutableMapOf(*pairs))

    infix fun String.with(instance: Any): String

    companion object
}
