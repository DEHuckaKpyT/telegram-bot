package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.container.CallbackMassageContainer
import io.github.dehuckakpyt.telegrambot.container.CommandMassageContainer
import io.github.dehuckakpyt.telegrambot.container.MassageContainer
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import kotlin.reflect.KClass


/**
 * Created on 12.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class ChainResolver(
    private val chainExceptionHandler: ChainExceptionHandler,
) : KoinComponent {

    private val callbackSerializer = get<CallbackSerializer>()

    private val actionByCommand: MutableMap<String, suspend CommandMassageContainer.() -> Unit> = hashMapOf()
    private val actionByStep: MutableMap<String, MutableMap<KClass<out MassageContainer>, suspend MassageContainer.() -> Unit>> =
        hashMapOf()
    private val actionByCallback: MutableMap<String, suspend CallbackMassageContainer.() -> Unit> = hashMapOf()

    fun addCommand(command: String, next: String? = null, action: suspend CommandMassageContainer.() -> Unit) {
        actionByCommand[command] = {
            next(next)
            action()
            finalize()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : MassageContainer> addStep(
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

    fun addCallback(callback: String, next: String? = null, action: suspend CallbackMassageContainer.() -> Unit) {
        callbackSerializer.validateCallbackName(callback)

        actionByCallback[callback] = {
            next(next)
            action()
            finalize()
        }
    }

    fun getCommand(command: String): suspend CommandMassageContainer.() -> Unit {
        return actionByCommand[command] ?: chainExceptionHandler.whenCommandNotFound(command)
    }

    fun getStep(step: String?, messageType: KClass<out MassageContainer>): suspend MassageContainer.() -> Unit {
        val actionByMessageType = step?.let(actionByStep::get) ?: chainExceptionHandler.whenStepNotFound()

        return actionByMessageType[messageType]
            ?: chainExceptionHandler.whenUnexpectedMessageType(actionByMessageType.keys)
    }

    fun getCallback(callback: String): (suspend CallbackMassageContainer.() -> Unit)? {
        return actionByCallback[callback]
    }
}