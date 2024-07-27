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
import java.text.SimpleDateFormat


/**
 * Created on 23.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class RegexTemplater(
    private val replaceRegex: Regex = Regex("(\\$\\{([a-zA-Z]+[a-zA-Z0-9_]*)\\})"),
    private val replaceNullWith: String = "",
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

    private fun buildMapTemplate(template: String, params: MutableMap<String, String?>): String {
        val find = replaceRegex.findAll(template)
        if (find.count() == 0) return template

        val builder = StringBuilder(template)

        find.toList().reversed().forEach { result ->
            val replacementRange = result.groups[1]?.range ?: return@forEach
            val replacementName = result.groups[2]?.value ?: return@forEach
            val replacement = params[replacementName] ?: replaceNullWith

            builder.replace(replacementRange.first, replacementRange.last + 1, replacement)
        }

        return builder.toString()
    }

    private companion object {
        private val convertToClass = object : TypeReference<MutableMap<String, String?>>() {}
    }
}