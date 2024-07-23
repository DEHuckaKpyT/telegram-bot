package io.github.dehuckakpyt.telegrambot.factory.template

import io.github.dehuckakpyt.telegrambot.context.SpringContext.autowired
import io.github.dehuckakpyt.telegrambot.ext.toKebabCase
import org.springframework.core.env.Environment
import kotlin.properties.PropertyDelegateProvider


/**
 * Created on 20.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
//TODO remove this
@Deprecated("Will be remove in future release. Use @ConfigurationProperties(\"telegram-bot.template\") class instead.")
object TemplateFactory {
    private val environment: Environment = autowired()
    private const val TEMPLATE_PATH_PREFIX = "telegram-bot.template."

    @Deprecated("Will be remove in future release. Use @ConfigurationProperties(\"telegram-bot.template\") class instead.")
    fun property(): PropertyDelegateProvider<Any?, Lazy<String>> = PropertyDelegateProvider { _, property ->
        lazy { getProperty(property.name.toKebabCase()) }
    }

    @Deprecated("Will be remove in future release. Use @ConfigurationProperties(\"telegram-bot.template\") class instead.")
    fun property(name: String): Lazy<String> = lazy { getProperty(name) }

    @Deprecated("Will be remove in future release. Use @ConfigurationProperties(\"telegram-bot.template\") class instead.")
    fun property(name: String, defaultTemplate: String): Lazy<String> = lazy {
        getPropertyOrNull(name) ?: defaultTemplate
    }

    private fun getProperty(templateName: String): String {
        return getPropertyOrNull(templateName) ?: throw RuntimeException("Did not find property '$templateName'")
    }

    private fun getPropertyOrNull(templateName: String): String? = environment.getProperty(TEMPLATE_PATH_PREFIX + templateName)
}