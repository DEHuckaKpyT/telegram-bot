package io.github.dehuckakpyt.telegrambot

import freemarker.template.Configuration
import freemarker.template.Version
import io.github.dehuckakpyt.telegrambot.container.CallbackMassageContainer
import io.github.dehuckakpyt.telegrambot.container.CommandMassageContainer
import io.github.dehuckakpyt.telegrambot.container.MassageContainer
import io.github.dehuckakpyt.telegrambot.container.MassageContainer.Companion.TEXT
import io.github.dehuckakpyt.telegrambot.container.TextMassageContainer
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSourceImpl
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSourceImpl
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSourceImpl
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
    bot: TelegramBot,
    username: String,
    messageSource: MessageSource = MessageSourceImpl(),
    chainSource: ChainSource = ChainSourceImpl(),
    callbackContentSource: CallbackContentSource = CallbackContentSourceImpl(),
    templateConfiguration: Configuration = Configuration(Version("2.3.32"))
) : BotChaining(
    application,
    bot,
    username,
    messageSource,
    chainSource,
    callbackContentSource,
    templateConfiguration
), KoinComponent {

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
        actionByCallback[callback] = {
            next(next)
            action()
            finalize()
        }
    }
}
