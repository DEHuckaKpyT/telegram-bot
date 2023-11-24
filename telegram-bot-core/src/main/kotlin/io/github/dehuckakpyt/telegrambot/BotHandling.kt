package io.github.dehuckakpyt.telegrambot

import io.github.dehuckakpyt.telegrambot.container.CallbackMessageContainer
import io.github.dehuckakpyt.telegrambot.container.CommandMessageContainer
import io.github.dehuckakpyt.telegrambot.container.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.MessageContainer.Companion.TEXT
import io.github.dehuckakpyt.telegrambot.container.TextMessageContainer
import io.github.dehuckakpyt.telegrambot.context.InternalKoinContext
import io.github.dehuckakpyt.telegrambot.resolver.ChainResolver
import io.github.dehuckakpyt.telegrambot.template.Templating
import io.ktor.server.application.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import kotlin.reflect.KClass


/**
 * Created on 18.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class BotHandling : KoinComponent, Templating {

    val application = get<Application>()
    private val chainResolver = InternalKoinContext.koin.get<ChainResolver>()

    fun command(command: String, next: String? = null, action: suspend CommandMessageContainer.() -> Unit) {
        chainResolver.addCommand(command, next, action)
    }

    fun step(step: String, next: String? = null, action: suspend TextMessageContainer.() -> Unit) {
        chainResolver.addStep(step, TEXT, next, action)
    }

    fun <T : MessageContainer> step(
        step: String,
        type: KClass<out T>,
        next: String? = null,
        action: suspend T.() -> Unit
    ) {
        chainResolver.addStep(step, type, next, action)
    }

    fun callback(callback: String, next: String? = null, action: suspend CallbackMessageContainer.() -> Unit) {
        chainResolver.addCallback(callback, next, action)
    }
}
