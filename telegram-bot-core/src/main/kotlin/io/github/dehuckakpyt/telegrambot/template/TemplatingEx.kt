package io.github.dehuckakpyt.telegrambot.template


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TemplatingEx : Templating {
    operator fun String.invoke(pair: Pair<String, Any>): String = Templating.buildTemplate(this, mapOf(pair))
    operator fun String.invoke(vararg pairs: Pair<String, Any>): String = Templating.buildTemplate(this, mapOf(*pairs))
    operator fun String.invoke(instance: Any): String = Templating.buildTemplate(this, instance)
}