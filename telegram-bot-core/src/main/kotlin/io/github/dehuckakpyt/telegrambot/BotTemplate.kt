package io.github.dehuckakpyt.telegrambot

import io.github.dehuckakpyt.telegrambot.ext.toKebabCase
import io.ktor.server.config.*
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatformTools
import kotlin.reflect.KProperty


class BotTemplate

val telegramBotTemplate = KoinPlatformTools.defaultContext().get().get<ApplicationConfig>(named("telegramBotTemplate"))

fun template(): BotTemplateDelegate = BotTemplateDelegate()

fun template(name: String): Lazy<String> = lazy {
    telegramBotTemplate.tryGetString(name) ?: throw RuntimeException("Не найдено шаблона $name")
}

fun template(name: String, defaultTemplate: String): Lazy<String> = lazy {
    telegramBotTemplate.tryGetString(name) ?: defaultTemplate
}

class BotTemplateDelegate {
    operator fun getValue(thisRef: Any, property: KProperty<*>): String {
        return telegramBotTemplate.tryGetString(property.name.toKebabCase())
            ?: throw RuntimeException("Не найдено шаблона для поля ${property.name}")

    }
}