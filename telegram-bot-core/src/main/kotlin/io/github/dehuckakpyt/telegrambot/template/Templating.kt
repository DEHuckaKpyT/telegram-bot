package io.github.dehuckakpyt.telegrambot.template

import com.dehucka.microservice.ext.mapper
import freemarker.template.Configuration
import freemarker.template.Template
import io.github.dehuckakpyt.telegrambot.template.model.method.CleanHtmlMethod
import io.github.dehuckakpyt.telegrambot.template.model.method.EscapeHtmlMethod
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import java.io.StringWriter


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface Templating {
    infix fun String.with(pair: Pair<String, Any>): String = buildMapTemplate(this, mutableMapOf(pair))

    fun String.with(vararg pairs: Pair<String, Any>): String = buildMapTemplate(this, mutableMapOf(*pairs))

    infix fun String.with(instance: Any): String = buildTemplate(this, instance)

    companion object : KoinComponent {
        private val templateConfiguration = get<Configuration>()
        private val cleanHtmlMethod = CleanHtmlMethod()
        private val escapeHtmlMethod = EscapeHtmlMethod()

        @Suppress("UNCHECKED_CAST")
        fun buildTemplate(template: String, instance: Any): String {
            return buildMapTemplate(template, mapper.convertValue(instance, MutableMap::class.java) as MutableMap<String, Any?>)
        }

        fun buildMapTemplate(template: String, params: MutableMap<String, Any?>): String {
            val writer = StringWriter()
            params["cleanHtml"] = cleanHtmlMethod
            params["escapeHtml"] = escapeHtmlMethod

            val markerTemplate = Template("template", template, templateConfiguration)
            markerTemplate.process(params, writer)

            return writer.toString()
        }
    }
}