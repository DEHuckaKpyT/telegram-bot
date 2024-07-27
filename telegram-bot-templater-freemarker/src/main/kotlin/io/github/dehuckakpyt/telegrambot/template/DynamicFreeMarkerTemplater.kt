package io.github.dehuckakpyt.telegrambot.template

import com.fasterxml.jackson.core.type.TypeReference
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
import freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS
import freemarker.template.Template
import freemarker.template.TemplateMethodModelEx
import java.io.StringWriter
import java.text.SimpleDateFormat


/**
 * Created on 26.07.2024.
 *
 * @author Denis Matytsin
 */
open class DynamicFreeMarkerTemplater(
    private val configuration: Configuration = Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS),
    private val methodsByName: Map<String, TemplateMethodModelEx> = emptyMap(),
) : Templater {
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

    override infix fun String.with(instance: Any): String = buildTemplate(this, instance)

    private fun buildTemplate(template: String, instance: Any): String {
        return buildMapTemplate(template, mapper.convertValue(instance, convertToClass))
    }

    private fun buildMapTemplate(template: String, params: MutableMap<String, Any?>): String {
        val writer = StringWriter()

        params.putAll(methodsByName)

        Template("template", template, configuration)
            .process(params, writer)

        return writer.toString()
    }

    private companion object {
        private val convertToClass = object : TypeReference<MutableMap<String, Any?>>() {}
    }
}