package io.github.dehuckakpyt.telegrambot.handling

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.api.TelegramBotApiExtHandling
import io.github.dehuckakpyt.telegrambot.container.CallbackContainer
import io.github.dehuckakpyt.telegrambot.container.GeneralContainer
import io.github.dehuckakpyt.telegrambot.container.message.CommandContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType.TEXT
import io.github.dehuckakpyt.telegrambot.container.message.TextMessageContainer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.converter.fromContentOrNull
import io.github.dehuckakpyt.telegrambot.factory.button.ButtonFactory
import io.github.dehuckakpyt.telegrambot.factory.input.InputFactory
import io.github.dehuckakpyt.telegrambot.resolver.ChainResolver
import io.github.dehuckakpyt.telegrambot.template.Templater
import kotlin.reflect.KClass


/**
 * Created on 18.07.2023.
 *
 * Class for creating handlers/dialog chains.
 *
 * <a href="https://dehuckakpyt.github.io/telegram-bot/message-chains.html">docs</a>
 *
 * @author Denis Matytsin
 */
class BotHandling internal constructor(
    public override val bot: TelegramBot,
    private val chainResolver: ChainResolver,
    private val contentConverter: ContentConverter,
    templater: Templater,
    buttonFactory: ButtonFactory,
) : TelegramBotApiExtHandling(),
    InputFactory,
    Templater by templater,
    ButtonFactory by buttonFactory {

    /**
     * Declare an action for the command.
     *
     * @param command name of the command, started with the '/' (for example, '/start', '/help')
     * @param next name of the next step (for example, 'get_name', 'get_phone')
     * @param action lambda, which will be invoked
     */
    fun command(command: String, next: String? = null, action: suspend CommandContainer.() -> Unit) {
        chainResolver.addCommand(command, next, action)
    }

    /**
     * Declare an action for the step.
     *
     * Default invoked for text messages.
     * If you want to handle other message types, see overload with type: KClass<out T> param.
     *
     * @param step name of the step (for example, 'get_name', 'get_phone')
     * @param next name of the next step (for example, 'get_name', 'get_phone')
     * @param action lambda, which will be invoked
     */
    fun step(step: String, next: String? = null, action: suspend TextMessageContainer.() -> Unit) {
        chainResolver.addStep(step, TEXT, next, action)
    }

    /**
     * Declare an action for the step.
     *
     * @param step name of the step (for example, 'get_name', 'get_phone')
     * @param type class of the MessageArgument (for example, TEXT, DOCUMENT) (see MessageType)
     * @param next name of the next step (for example, 'get_name', 'get_phone')
     * @param action lambda, which will be invoked
     *
     * @see io.github.dehuckakpyt.telegrambot.container.message.MessageType
     */
    fun <T : MessageContainer> step(
        step: String,
        type: KClass<out T>,
        next: String? = null,
        action: suspend T.() -> Unit,
    ) {
        chainResolver.addStep(step, type, next, action)
    }

    /**
     * Declare an action for the callback.
     *
     * @param callback callback name (sets in ButtonFactory.callbackButton())
     * @param next name of the next step (for example, 'get_name', 'get_phone')
     * @param action lambda, which will be invoked
     *
     * @see io.github.dehuckakpyt.telegrambot.factory.button.ButtonFactory
     */
    fun callback(callback: String, next: String? = null, action: suspend CallbackContainer.() -> Unit) {
        chainResolver.addCallback(callback, next, action)
    }

    /**
     * Set next step dynamically.
     *
     * @param step name of the next step
     */
    fun GeneralContainer.next(step: String?) {
        nextStep = step
    }

    /**
     * Set next step dynamically.
     *
     * Same as 'fun Argument.next(step: String?)' plus 'fun Argument.transfer(instance: Any)'
     *
     * @param step name of the next step
     * @param instance object for transfer
     */
    fun GeneralContainer.next(step: String, instance: Any) {
        nextStep = step
        nextStepInstance = instance
    }

    /**
     * Save any object to use it in the next step.
     *
     * @param instance any object
     */
    fun GeneralContainer.transfer(instance: Any) {
        nextStepInstance = instance
    }

    /**
     * Get saved object in the previous step.
     *
     * @return object of selected class
     */
    inline fun <reified T> GeneralContainer.transferred(): T {
        return transferredOrNull()
            ?: throw RuntimeException("An instance of class ${T::class.simpleName} is expected, but nothing is stored in chainSource.content.")
    }

    /**
     * Get saved object in the previous step.
     *
     * @return if exists then object of selected class else null
     */
    inline fun <reified T : Any> GeneralContainer.transferredOrNull(): T? = transferredOrNull(T::class)

    /**
     * Get saved object in the previous step.
     *
     * @param clazz class of saved object
     *
     * @return if exists then object of selected class else null
     */
    fun <T : Any> GeneralContainer.transferredOrNull(clazz: KClass<T>): T? = contentConverter.fromContentOrNull(content, clazz)
}
