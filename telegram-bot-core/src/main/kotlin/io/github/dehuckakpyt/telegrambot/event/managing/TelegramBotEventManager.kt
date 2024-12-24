package io.github.dehuckakpyt.telegrambot.event.managing

//import kotlinx.coroutines.CoroutineName
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.channels.Channel
//import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
//import kotlinx.coroutines.launch


/**
 * Created on 21.12.2024.
 *
 * @author Denis Matytsin
 */
class TelegramBotEventManager internal constructor() {
//    private val scope = CoroutineScope(Dispatchers.Default + CoroutineName("TelegramBotEventManager"))
    //    private val channel: Channel<Pair<String, Map<String, Any?>>> = Channel(BUFFERED)

    private val actionsAfterMethodCalledByMethodName: MutableMap<String, MutableList<suspend (Map<String, Any?>) -> Unit>> = mutableMapOf()

//    init {
//        scope.launch {
//            for ((methodName, args) in channel) {
//                actionsAfterMethodCalledByMethodName[methodName]?.forEach { action -> action.invoke(args) }
//            }
//        }
//    }

    internal suspend inline fun <T> sendAfterMethodEvent(methodName: String, returnedValue: T, crossinline block: MutableMap<String, Any?>.() -> Unit): Unit {
        if (methodName !in actionsAfterMethodCalledByMethodName.keys) return

        val args = buildMap {
            block()
            put("returnedValue", returnedValue)
        }
        actionsAfterMethodCalledByMethodName[methodName]?.forEach { action -> action.invoke(args) }
    }

    fun afterMethodCalled(methodName: String, action: suspend (Map<String, Any?>) -> Unit): Unit {
        actionsAfterMethodCalledByMethodName.getOrPut(methodName) { mutableListOf() }.add(action)
    }
}
