package io.github.dehuckakpyt.telegrambot.strategy.invocation

import io.github.dehuckakpyt.telegrambot.sync.ActionSynchronizer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


/**
 * Created on 07.06.2024.
 *
 * A strategy that allows handlers to be executed for each chat independently of other chats.
 * But actions in the same chat will be executed strictly sequentially.
 * It may be useful for private chats only bots.
 *
 * @author Denis Matytsin
 */
internal class HandlerInvocationChatSyncStrategy(
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob()),
) : HandlerInvocationStrategy {
    private val synchronizer = ActionSynchronizer<Long>(scope)

    override suspend fun invokeHandler(chatId: Long, fromId: Long, action: suspend () -> Unit): Unit {
        synchronizer.parallelisedBy(chatId, action)
    }
}