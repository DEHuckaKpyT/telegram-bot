package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.argument.Argument
import io.github.dehuckakpyt.telegrambot.argument.CallbackArgument
import io.github.dehuckakpyt.telegrambot.argument.message.CommandArgument
import io.github.dehuckakpyt.telegrambot.argument.message.MessageArgument
import io.github.dehuckakpyt.telegrambot.context.InternalKoinComponent
import io.github.dehuckakpyt.telegrambot.context.getInternal
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import org.koin.core.component.get
import kotlin.reflect.KClass


/**
 * Created on 12.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class ChainResolver : InternalKoinComponent {

    private val callbackSerializer = get<CallbackSerializer>()
    private val chainSource = get<ChainSource>()
    private val chainExceptionHandler = getInternal<ChainExceptionHandler>()
    private val contentConverter = getInternal<ContentConverter>()

    private val actionByCommand: MutableMap<String, suspend CommandArgument.() -> Unit> = hashMapOf()
    private val actionByStep: MutableMap<String, MutableMap<KClass<out MessageArgument>, suspend MessageArgument.() -> Unit>> =
        hashMapOf()
    private val actionByCallback: MutableMap<String, suspend CallbackArgument.() -> Unit> = hashMapOf()

    fun addCommand(command: String, next: String? = null, action: suspend CommandArgument.() -> Unit) {
        actionByCommand[command] = {
            this.nextStep = next
            action()
            saveNextStepInChain()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : MessageArgument> addStep(
        step: String,
        type: KClass<out T>,
        next: String? = null,
        action: suspend T.() -> Unit
    ) {
        val actionByType = actionByStep.getOrPut(step) { hashMapOf() }

        actionByType[type] = {
            this.nextStep = next
            (action as suspend MessageArgument.() -> Unit)(this)
            saveNextStepInChain()
        }
    }

    fun addCallback(callback: String, next: String? = null, action: suspend CallbackArgument.() -> Unit) {
        callbackSerializer.validateCallbackName(callback)

        actionByCallback[callback] = {
            this.nextStep = next
            action()
            saveNextStepInChain()
        }
    }

    fun getCommand(command: String): suspend CommandArgument.() -> Unit {
        return actionByCommand[command] ?: chainExceptionHandler.whenCommandNotFound(command)
    }

    fun getStep(step: String?, messageType: KClass<out MessageArgument>): suspend MessageArgument.() -> Unit {
        val actionByMessageType = step?.let(actionByStep::get) ?: chainExceptionHandler.whenStepNotFound()

        return actionByMessageType[messageType] ?: chainExceptionHandler.whenUnexpectedMessageType()
    }

    fun getCallback(callback: String): (suspend CallbackArgument.() -> Unit)? {
        return actionByCallback[callback]
    }

    private suspend fun Argument.saveNextStepInChain() {
        chainSource.save(chatId, from.id, nextStep, nextStepInstance.toContent())
    }

    private fun Any?.toContent(): String? = contentConverter.toContentOrNull(this)
}