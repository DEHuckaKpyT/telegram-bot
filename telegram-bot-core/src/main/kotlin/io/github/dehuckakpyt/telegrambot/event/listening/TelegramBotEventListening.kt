package io.github.dehuckakpyt.telegrambot.event.listening

import io.github.dehuckakpyt.telegrambot.event.managing.TelegramBotEventManager
import io.github.dehuckakpyt.telegrambot.ext.event.listening.defaults
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource


/**
 * Created on 23.12.2024.
 *
 * @author Denis Matytsin
 */
public class TelegramBotEventListening(
    messageSource: MessageSource,
    private val manager: TelegramBotEventManager,
    preventDefaults: Boolean,
) {

    init {
        if (preventDefaults.not()) defaults(messageSource)
    }

    public fun afterMethodCalled(methodName: String, action: suspend (Map<String, Any?>) -> Unit): Unit = manager.afterMethodCalled(methodName, action)

    public infix fun After.method(methodName: String): AfterMethod = AfterMethod(methodName)
    public infix fun AfterMethod.called(action: suspend (Map<String, Any?>) -> Unit) = afterMethodCalled(methodName, action)
}

object After
class AfterMethod(internal val methodName: String)
typealias after = After
