package io.github.dehuckakpyt.telegrambot.factory.template

import io.github.dehuckakpyt.telegrambot.context.InternalKoinContext
import io.github.dehuckakpyt.telegrambot.ext.toKebabCase
import io.ktor.server.config.*
import org.koin.core.qualifier.named
import kotlin.properties.PropertyDelegateProvider


/**
 * Created on 20.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
object TemplateFactory {
    private val telegramBotTemplate = InternalKoinContext.koin.get<ApplicationConfig>(named("telegramBotTemplate"))

    fun property(): PropertyDelegateProvider<Any?, Lazy<String>> = PropertyDelegateProvider { _, property ->
        lazy { getProperty(property.name.toKebabCase()) }
    }

    fun property(name: String): Lazy<String> = lazy { getProperty(name) }

    fun property(name: String, defaultTemplate: String): Lazy<String> = lazy {
        getPropertyOrNull(name) ?: defaultTemplate
    }

    private fun getProperty(templateName: String): String {
        return getPropertyOrNull(templateName) ?: throw RuntimeException("Did not find property '$templateName'")
    }

    private fun getPropertyOrNull(templateName: String): String? = telegramBotTemplate.tryGetString(templateName)
}