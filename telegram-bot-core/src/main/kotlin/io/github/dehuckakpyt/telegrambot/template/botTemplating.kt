package io.github.dehuckakpyt.telegrambot.template

import io.github.dehuckakpyt.telegrambot.ext.toKebabCase
import io.ktor.server.config.*
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatformTools
import kotlin.properties.PropertyDelegateProvider


class BotTemplate

private val telegramBotTemplate =
    KoinPlatformTools.defaultContext().get().get<ApplicationConfig>(named("telegramBotTemplate"))

fun template(): PropertyDelegateProvider<Nothing?, Lazy<String>> = PropertyDelegateProvider { _, property ->
    lazy { getTemplate(property.name.toKebabCase()) }
}

fun template(name: String): Lazy<String> = lazy { getTemplate(name) }

fun template(name: String, defaultTemplate: String): Lazy<String> = lazy {
    getTemplateOrNull(name) ?: defaultTemplate
}

private fun getTemplate(templateName: String): String {
    return getTemplateOrNull(templateName) ?: throw RuntimeException("Не найден шаблон '$templateName'")
}

private fun getTemplateOrNull(templateName: String): String? = telegramBotTemplate.tryGetString(templateName)
