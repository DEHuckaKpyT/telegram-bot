package io.github.dehuckakpyt.telegrambot.template

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import freemarker.template.Configuration
import freemarker.template.Template
import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatter
import io.github.dehuckakpyt.telegrambot.template.model.method.CleanHtmlMethod
import io.github.dehuckakpyt.telegrambot.template.model.method.EscapeHtmlMethod
import java.io.StringWriter
import java.text.SimpleDateFormat


/**
 * Created on 23.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class TemplaterImpl(
    private val templateConfiguration: Configuration,
    htmlFormatter: HtmlFormatter,
) : Templater {
    private val cleanHtmlMethod = CleanHtmlMethod(htmlFormatter)
    private val escapeHtmlMethod = EscapeHtmlMethod(htmlFormatter)

    private val mapper = jacksonMapperBuilder().run {
        configure(MapperFeature.USE_ANNOTATIONS, false)
        configure(SerializationFeature.INDENT_OUTPUT, true)
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }.build().apply {
        setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
            indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
            indentObjectsWith(DefaultIndenter("  ", "\n"))
        })
        dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        registerModule(JavaTimeModule())

        registerModule(
            KotlinModule.Builder()
                .withReflectionCacheSize(512)
                .configure(KotlinFeature.NullToEmptyCollection, false)
                .configure(KotlinFeature.NullToEmptyMap, false)
                .configure(KotlinFeature.NullIsSameAsDefault, false)
                .configure(KotlinFeature.SingletonSupport, false)
                .configure(KotlinFeature.StrictNullChecks, false)
                .build()
        )
    }

    override infix fun String.with(pair: Pair<String, Any>): String = buildMapTemplate(this, mutableMapOf(pair))

    override fun String.with(vararg pairs: Pair<String, Any>): String = buildMapTemplate(this, mutableMapOf(*pairs))

    override infix fun String.with(instance: Any): String = buildTemplate(this, instance)

    @Suppress("UNCHECKED_CAST")
    private fun buildTemplate(template: String, instance: Any): String {
        return buildMapTemplate(template, mapper.convertValue(instance, MutableMap::class.java) as MutableMap<String, Any?>)
    }

    private fun buildMapTemplate(template: String, params: MutableMap<String, Any?>): String {
        val writer = StringWriter()
        params["cleanHtml"] = cleanHtmlMethod
        params["escapeHtml"] = escapeHtmlMethod

        val markerTemplate = Template("template", template, templateConfiguration)
        markerTemplate.process(params, writer)

        return writer.toString()
    }
}