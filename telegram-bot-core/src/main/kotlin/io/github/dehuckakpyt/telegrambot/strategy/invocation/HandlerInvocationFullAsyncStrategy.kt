package io.github.dehuckakpyt.telegrambot.strategy.invocation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


/**
 * Created on 07.06.2024.
 *
 * @author Denis Matytsin
 */
internal class HandlerInvocationFullAsyncStrategy(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob()),
) : HandlerInvocationStrategy {

    override suspend fun invokeHandler(chatId: Long, fromId: Long, action: suspend () -> Unit): Unit {
        scope.launch { action() }
    }
}