package io.github.dehuckakpyt.telegrambot.template.model.method

import freemarker.template.SimpleScalar
import freemarker.template.TemplateMethodModelEx
import io.github.dehuckakpyt.telegrambot.template.formatter.HtmlFormatter


/**
 * Created on 08.10.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class EscapeHtmlMethod(
    private val htmlFormatter: HtmlFormatter,
) : TemplateMethodModelEx {

    override fun exec(arguments: MutableList<Any?>): Any? {
        if (arguments.size != 1) throw RuntimeException("Expected only one argument")

        val any = arguments[0]
        any ?: return null
        if (any !is SimpleScalar) throw RuntimeException("Expected string value")

        return SimpleScalar(htmlFormatter.escapeHtml(any.asString))
    }
}