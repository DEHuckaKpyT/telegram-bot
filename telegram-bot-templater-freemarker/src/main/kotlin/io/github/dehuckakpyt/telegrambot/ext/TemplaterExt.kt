package io.github.dehuckakpyt.telegrambot.ext

import freemarker.template.Configuration
import freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS
import freemarker.template.TemplateMethodModelEx
import io.github.dehuckakpyt.telegrambot.template.DynamicFreeMarkerTemplater
import io.github.dehuckakpyt.telegrambot.template.Templater
import io.github.dehuckakpyt.telegrambot.template.formatter.HtmlFormatter
import io.github.dehuckakpyt.telegrambot.template.formatter.HtmlFormatterImpl
import io.github.dehuckakpyt.telegrambot.template.model.method.CleanHtmlMethod
import io.github.dehuckakpyt.telegrambot.template.model.method.EscapeHtmlMethod


/**
 * Created on 26.07.2024.
 *
 * @author Denis Matytsin
 */
val Templater.Companion.dynamicFreeMarker get() = Templater.Companion.dynamicFreeMarker()

fun Templater.Companion.dynamicFreeMarker(
    configuration: Configuration = Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS),
    htmlFormatter: HtmlFormatter = HtmlFormatterImpl(),
): Templater {
    val methodsByName = buildMap {
        put("cleanHtml", CleanHtmlMethod(htmlFormatter))
        put("escapeHtml", EscapeHtmlMethod(htmlFormatter))
    }

    return Templater.Companion.dynamicFreeMarker(methodsByName, configuration)
}

fun Templater.Companion.dynamicFreeMarker(
    methodsByName: Map<String, TemplateMethodModelEx>,
    configuration: Configuration = Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS),
): Templater = DynamicFreeMarkerTemplater(configuration, methodsByName)