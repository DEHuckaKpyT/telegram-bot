package io.github.dehuckakpyt.telegrambot.template

import com.dehucka.microservice.ext.mapperConfig
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import freemarker.template.Configuration
import freemarker.template.Template
import io.github.dehuckakpyt.telegrambot.context.InternalKoinComponent
import io.github.dehuckakpyt.telegrambot.context.getInternal
import io.github.dehuckakpyt.telegrambot.template.model.method.CleanHtmlMethod
import io.github.dehuckakpyt.telegrambot.template.model.method.EscapeHtmlMethod
import java.io.StringWriter
import kotlin.collections.set


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

    companion object : InternalKoinComponent {
        private val templateConfiguration = getInternal<Configuration>()
        private val cleanHtmlMethod = CleanHtmlMethod()
        private val escapeHtmlMethod = EscapeHtmlMethod()
        private val mapper = jacksonMapperBuilder().run {
            configure(MapperFeature.USE_ANNOTATIONS, false)
        }.build().apply { mapperConfig() }

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