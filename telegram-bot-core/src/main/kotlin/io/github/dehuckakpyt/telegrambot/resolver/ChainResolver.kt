package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.container.CallbackContainer
import io.github.dehuckakpyt.telegrambot.container.GeneralContainer
import io.github.dehuckakpyt.telegrambot.container.message.CommandContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import io.github.dehuckakpyt.telegrambot.ext.container.chatId
import io.github.dehuckakpyt.telegrambot.manager.chain.ChainManager
import kotlin.reflect.KClass


/**
 * Created on 12.11.2023.
 *
 * Resolver for chain actions.
 *
 * Here you can add and retrieve actions for commands, steps and callbacks.
 *
 * @author Denis Matytsin
 */
internal class ChainResolver(
    private val callbackSerializer: CallbackSerializer,
    private val chainExceptionHandler: ChainExceptionHandler,
    private val chainManager: ChainManager,
) {

    private val actionByCommand: MutableMap<String, suspend CommandContainer.() -> Unit> = hashMapOf()
    private val actionByStep: MutableMap<String, MutableMap<KClass<out MessageContainer>, suspend MessageContainer.() -> Unit>> = hashMapOf()
    private val actionByCallback: MutableMap<String, suspend CallbackContainer.() -> Unit> = hashMapOf()

    /**
     * Add command handler.
     *
     * @param command name of the command, started with the '/' (for example, '/start', '/help')
     * @param next name of the next step (for example, 'get_name', 'get_phone')
     * @param action lambda, which will be invoked
     */
    fun addCommand(command: String, next: String? = null, action: suspend CommandContainer.() -> Unit) {
        actionByCommand[command] = wrapAction(next, action)
    }

    /**
     * Add step handler.
     *
     * @param step name of the step (for example, 'get_name', 'get_phone')
     * @param type class of the MessageArgument (for example, TEXT, DOCUMENT) (see MessageType)
     * @param next name of the next step (for example, 'get_name', 'get_phone')
     * @param action lambda, which will be invoked
     *
     * @see io.github.dehuckakpyt.telegrambot.container.message.MessageType
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : MessageContainer> addStep(
        step: String,
        type: KClass<out T>,
        next: String? = null,
        action: suspend T.() -> Unit,
    ) {
        val actionByType = actionByStep.getOrPut(step) { hashMapOf() }

        actionByType[type] = wrapAction(next, action as suspend MessageContainer.() -> Unit)
    }

    /**
     * Add callback handler.
     *
     * @param callback callback name (sets in ButtonFactory.callbackButton())
     * @param next name of the next step (for example, 'get_name', 'get_phone')
     * @param action lambda, which will be invoked
     *
     * @see io.github.dehuckakpyt.telegrambot.factory.button.ButtonFactory
     */
    fun addCallback(callback: String, next: String? = null, action: suspend CallbackContainer.() -> Unit) {
        callbackSerializer.validateCallbackName(callback)

        actionByCallback[callback] = wrapAction(next, action)
    }

    /**
     * Get command handler.
     *
     * @param command name of the command, started with the '/' (for example, '/start', '/help')
     */
    fun getCommand(command: String): suspend CommandContainer.() -> Unit {
        return actionByCommand[command] ?: chainExceptionHandler.whenCommandNotFound(command)
    }

    /**
     * Get step handler.
     *
     * @param step name of the step (for example, 'get_name', 'get_phone')
     * @param type class of the MessageArgument (for example, TEXT, DOCUMENT) (see MessageType)
     *
     * @see io.github.dehuckakpyt.telegrambot.container.message.MessageType
     */
    fun getStep(step: String, type: KClass<out MessageContainer>): (suspend MessageContainer.() -> Unit)? {
        val actionByMessageType = step.let(actionByStep::get) ?: return null

        return actionByMessageType[type] ?: chainExceptionHandler.whenUnexpectedMessageType()
    }

    /**
     * Get callback handler.
     *
     * @param callback callback name (sets in ButtonFactory.callbackButton())
     *
     * @see io.github.dehuckakpyt.telegrambot.factory.button.ButtonFactory
     */
    fun getCallback(callback: String): (suspend CallbackContainer.() -> Unit)? {
        return actionByCallback[callback]
    }

    internal val allowedUpdates: Set<String>
        get() = buildSet {
            if (actionByCommand.isNotEmpty() || actionByStep.isNotEmpty()) add("message")
            if (actionByCallback.isNotEmpty()) add("callback_query")
        }

    /**
     * Wrap chain action.
     *
     * The lambda needs to be wrapped with additional actions to make the dynamic chains work.
     *
     * @param next name of the next step (for example, 'get_name', 'get_phone')
     * @param action action needed to wrap
     *
     * @return lambda with applied dynamic step
     */
    private fun <T : GeneralContainer> wrapAction(next: String?, action: suspend T.() -> Unit): suspend T.() -> Unit = {
        // First, set name ot the next step from static param.
        this.nextStep = next
        // Invoke action, which can change next step.
        this.action()
        // Save name ot the next step and transferred object.
        this.saveNextStepInChain()
    }

    /**
     * Save state of the current chain.
     *
     * Will be saved name of the next step and transferred object.
     */
    private suspend fun GeneralContainer.saveNextStepInChain() {
        chainManager.setNextStep(chatId, from.id, nextStep, nextStepInstance)
    }
}