package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.container.CallbackMessageContainer
import io.github.dehuckakpyt.telegrambot.container.CommandMessageContainer
import io.github.dehuckakpyt.telegrambot.container.MessageContainer
import io.github.dehuckakpyt.telegrambot.context.InternalKoinComponent
import io.github.dehuckakpyt.telegrambot.context.getInternal
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
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
    private val chainExceptionHandler = getInternal<ChainExceptionHandler>()

    private val actionByCommand: MutableMap<String, suspend CommandMessageContainer.() -> Unit> = hashMapOf()
    private val actionByStep: MutableMap<String, MutableMap<KClass<out MessageContainer>, suspend MessageContainer.() -> Unit>> =
        hashMapOf()
    private val actionByCallback: MutableMap<String, suspend CallbackMessageContainer.() -> Unit> = hashMapOf()

    fun addCommand(command: String, next: String? = null, action: suspend CommandMessageContainer.() -> Unit) {
        actionByCommand[command] = {
            next(next)
            action()
            finalize()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : MessageContainer> addStep(
        step: String,
        type: KClass<out T>,
        next: String? = null,
        action: suspend T.() -> Unit
    ) {
        val actionByType = actionByStep.getOrPut(step) { hashMapOf() }

        actionByType[type] = {
            next(next)
            (action as suspend MessageContainer.() -> Unit)(this)
            finalize()
        }
    }

    fun addCallback(callback: String, next: String? = null, action: suspend CallbackMessageContainer.() -> Unit) {
        callbackSerializer.validateCallbackName(callback)

        actionByCallback[callback] = {
            next(next)
            action()
            finalize()
        }
    }

    fun getCommand(command: String): suspend CommandMessageContainer.() -> Unit {
        return actionByCommand[command] ?: chainExceptionHandler.whenCommandNotFound(command)
    }

    fun getStep(step: String?, messageType: KClass<out MessageContainer>): suspend MessageContainer.() -> Unit {
        val actionByMessageType = step?.let(actionByStep::get) ?: chainExceptionHandler.whenStepNotFound()

        return actionByMessageType[messageType]
            ?: chainExceptionHandler.whenUnexpectedMessageType(actionByMessageType.keys)
    }

    fun getCallback(callback: String): (suspend CallbackMessageContainer.() -> Unit)? {
        return actionByCallback[callback]
    }
}