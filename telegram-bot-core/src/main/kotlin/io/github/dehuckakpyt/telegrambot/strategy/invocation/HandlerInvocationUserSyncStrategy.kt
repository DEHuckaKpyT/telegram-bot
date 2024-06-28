package io.github.dehuckakpyt.telegrambot.strategy.invocation

import io.github.dehuckakpyt.telegrambot.sync.ActionSynchronizer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


/**
 * Created on 07.06.2024.
 *
 * A strategy that allows handlers to be executed for each user independently of other users.
 * But actions of the same user in all chats will be executed strictly sequentially.
 *
 * @author Denis Matytsin
 */
internal class HandlerInvocationUserSyncStrategy(
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob()),
) : HandlerInvocationStrategy {
    private val synchronizer = ActionSynchronizer<Long>(scope)

    override suspend fun invokeHandler(chatId: Long, fromId: Long, action: suspend () -> Unit): Unit {
        synchronizer.parallelisedBy(fromId, action)
    }
}