package io.github.dehuckakpyt.telegrambot.listener.event


/**
 * Created on 21.12.2024.
 *
 * @author Denis Matytsin
 */
class TelegramBotEventListener {
    private val actionsByMethodName: Map<String, List<suspend (Map<String, Any?>) -> Unit>> = mutableMapOf()

    internal suspend inline fun <T> sendAfterEvent(methodName: String, returnValue: T, crossinline block: MutableMap<String, Any?>.() -> Unit): Unit {
        if (methodName !in actionsByMethodName.keys) return

        val args = buildMap {
            block()
            put("returnedValue", returnValue)
        }
        actionsByMethodName[methodName]?.forEach { action -> action.invoke(args) }
    }
}
