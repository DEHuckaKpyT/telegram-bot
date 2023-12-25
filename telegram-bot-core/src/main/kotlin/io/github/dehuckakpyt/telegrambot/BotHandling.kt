package io.github.dehuckakpyt.telegrambot

import io.github.dehuckakpyt.telegrambot.api.TelegramApiHandling
import io.github.dehuckakpyt.telegrambot.argument.Argument
import io.github.dehuckakpyt.telegrambot.argument.CallbackArgument
import io.github.dehuckakpyt.telegrambot.argument.message.CommandArgument
import io.github.dehuckakpyt.telegrambot.argument.message.MessageArgument
import io.github.dehuckakpyt.telegrambot.argument.message.MessageType.TEXT
import io.github.dehuckakpyt.telegrambot.argument.message.TextMessageArgument
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.factory.button.ButtonFactory
import io.github.dehuckakpyt.telegrambot.resolver.ChainResolver
import io.github.dehuckakpyt.telegrambot.template.Templating
import kotlin.reflect.KClass


/**
 * Created on 18.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class BotHandling internal constructor(
    public override val bot: TelegramBot,
    private val chainResolver: ChainResolver,
    private val contentConverter: ContentConverter,
    templating: Templating,
    buttonFactory: ButtonFactory,
) : TelegramApiHandling(),
    Templating by templating,
    ButtonFactory by buttonFactory {

    fun command(command: String, next: String? = null, action: suspend CommandArgument.() -> Unit) {
        chainResolver.addCommand(command, next, action)
    }

    fun step(step: String, next: String? = null, action: suspend TextMessageArgument.() -> Unit) {
        chainResolver.addStep(step, TEXT, next, action)
    }

    fun <T : MessageArgument> step(
        step: String,
        type: KClass<out T>,
        next: String? = null,
        action: suspend T.() -> Unit,
    ) {
        chainResolver.addStep(step, type, next, action)
    }

    fun callback(callback: String, next: String? = null, action: suspend CallbackArgument.() -> Unit) {
        chainResolver.addCallback(callback, next, action)
    }

    fun Argument.next(step: String?) {
        nextStep = step
    }

    fun Argument.next(step: String, instance: Any) {
        nextStep = step
        nextStepInstance = instance
    }

    fun Argument.finalizeChain() = next(null)

    fun Argument.transfer(instance: Any) {
        nextStepInstance = instance
    }

    inline fun <reified T> Argument.transferred(): T {
        return transferredOrNull()
            ?: throw RuntimeException("Ожидается экземпляр класса ${T::class.simpleName}, но в chainSource.content ничего не сохранено.")
    }

    inline fun <reified T : Any> Argument.transferredOrNull(): T? = transferredOrNull(T::class)

    fun <T : Any> Argument.transferredOrNull(clazz: KClass<T>): T? = contentConverter.fromContentOrNull(content, clazz)

    private fun Any?.toContent(): String? = contentConverter.toContentOrNull(this)
}
