package io.github.dehuckakpyt.telegrambot

import com.elbekd.bot.Bot
import freemarker.template.Configuration
import freemarker.template.Version
import io.github.dehuckakpyt.telegrambot.data.container.CallbackMassageContainer
import io.github.dehuckakpyt.telegrambot.data.container.CommandMassageContainer
import io.github.dehuckakpyt.telegrambot.data.container.MassageContainer
import io.github.dehuckakpyt.telegrambot.data.container.TextMassageContainer
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSourceImpl
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSourceImpl
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSourceImpl
import io.ktor.server.application.*
import kotlin.reflect.KClass


/**
 * Created on 18.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class BotHandling(
    application: Application,
    bot: Bot,
    username: String,
    messageSource: MessageSource = MessageSourceImpl(),
    chainSource: ChainSource = ChainSourceImpl(),
    callbackContentSource: CallbackContentSource = CallbackContentSourceImpl(),
    templateConfiguration: Configuration = Configuration(Version("2.3.32"))
) : TelegramBotChaining(
    application,
    bot,
    username,
    messageSource,
    chainSource,
    callbackContentSource,
    templateConfiguration
) {

    fun command(
        command: String,
        next: String? = null,
        action: suspend CommandMassageContainer.() -> Unit
    ) {
        actionByCommand[command] = {
            next(next)
            action()
            finalize()
        }
    }

    fun step(
        step: String,
        next: String? = null,
        action: suspend TextMassageContainer.() -> Unit
    ) {
        actionByStep[step] = {
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
        actionByStep[step] = {
            next(next)
            (action as suspend MassageContainer.() -> Unit)(this)
            finalize()
        }
    }

    fun callback(
        callback: String,
        next: String? = null,
        action: suspend CallbackMassageContainer.() -> Unit
    ) {
        actionByCallback[callback] = {
            next(next)
            action()
            finalize()
        }
    }
}
