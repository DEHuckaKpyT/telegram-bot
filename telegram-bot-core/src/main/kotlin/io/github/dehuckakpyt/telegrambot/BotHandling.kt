package io.github.dehuckakpyt.telegrambot

import io.github.dehuckakpyt.telegrambot.container.CallbackMassageContainer
import io.github.dehuckakpyt.telegrambot.container.CommandMassageContainer
import io.github.dehuckakpyt.telegrambot.container.MassageContainer
import io.github.dehuckakpyt.telegrambot.container.MassageContainer.Companion.TEXT
import io.github.dehuckakpyt.telegrambot.container.TextMassageContainer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
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
    application: Application,
    contentConverter: ContentConverter,
    bot: TelegramBot,
    username: String
) : BotChaining(application, contentConverter, bot, username), KoinComponent {

    fun command(command: String, next: String? = null, action: suspend CommandMassageContainer.() -> Unit) {
        actionByCommand[command] = {
            next(next)
            action()
            finalize()
        }
    }

    fun step(step: String, next: String? = null, action: suspend TextMassageContainer.() -> Unit) {
        val actionByType = actionByStep.getOrPut(step) { hashMapOf() }

        actionByType[TEXT] = {
            next(next)
            (this as TextMassageContainer).action()
            finalize()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : MassageContainer> step(
        step: String,
        type: KClass<out T>,
        next: String? = null,
        action: suspend T.() -> Unit
    ) {
        val actionByType = actionByStep.getOrPut(step) { hashMapOf() }

        actionByType[type] = {
            next(next)
            (action as suspend MassageContainer.() -> Unit)(this)
            finalize()
        }
    }

    fun callback(callback: String, next: String? = null, action: suspend CallbackMassageContainer.() -> Unit) {
        callbackSerializer.validateCallbackName(callback)

        actionByCallback[callback] = {
            next(next)
            action()
            finalize()
        }
    }
}
