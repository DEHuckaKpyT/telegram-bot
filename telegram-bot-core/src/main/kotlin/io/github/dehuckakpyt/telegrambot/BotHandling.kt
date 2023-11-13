package io.github.dehuckakpyt.telegrambot

import io.github.dehuckakpyt.telegrambot.container.CallbackMassageContainer
import io.github.dehuckakpyt.telegrambot.container.CommandMassageContainer
import io.github.dehuckakpyt.telegrambot.container.MassageContainer
import io.github.dehuckakpyt.telegrambot.container.MassageContainer.Companion.TEXT
import io.github.dehuckakpyt.telegrambot.container.TextMassageContainer
import io.github.dehuckakpyt.telegrambot.resolver.ChainResolver
import io.github.dehuckakpyt.telegrambot.template.Templating
import io.ktor.server.application.*
import org.koin.core.component.KoinComponent
import kotlin.reflect.KClass


/**
 * Created on 18.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class BotHandling(
    val application: Application,
    private val chainResolver: ChainResolver,
) : KoinComponent, Templating {

    fun command(command: String, next: String? = null, action: suspend CommandMassageContainer.() -> Unit) {
        chainResolver.addCommand(command, next, action)
    }

    fun step(step: String, next: String? = null, action: suspend TextMassageContainer.() -> Unit) {
        chainResolver.addStep(step, TEXT, next, action)
    }

    fun <T : MassageContainer> step(
        step: String,
        type: KClass<out T>,
        next: String? = null,
        action: suspend T.() -> Unit
    ) {
        chainResolver.addStep(step, type, next, action)
    }

    fun callback(callback: String, next: String? = null, action: suspend CallbackMassageContainer.() -> Unit) {
        chainResolver.addCallback(callback, next, action)
    }
}
