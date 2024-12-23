package io.github.dehuckakpyt.telegrambot.event.managing


/**
 * Created on 21.12.2024.
 *
 * @author Denis Matytsin
 */
class TelegramBotEventManager {
    private val actionsAfterMethodCalledByMethodName: Map<String, MutableList<suspend (Map<String, Any?>) -> Unit>> = mutableMapOf()

    internal suspend inline fun <T> sendAfterMethodEvent(methodName: String, returnedValue: T, crossinline block: MutableMap<String, Any?>.() -> Unit): Unit {
        if (methodName !in actionsAfterMethodCalledByMethodName.keys) return

        val args = buildMap {
            block()
            put("returnedValue", returnedValue)
        }
        actionsAfterMethodCalledByMethodName[methodName]?.forEach { action -> action.invoke(args) }
    }

    public fun afterMethodCalled(methodName: String, action: suspend (Map<String, Any?>) -> Unit): Unit {
        actionsAfterMethodCalledByMethodName.getOrDefault(methodName, mutableListOf()).add(action)
    }
}
