package io.github.dehuckakpyt.telegrambot.strategy.invocation

import io.github.dehuckakpyt.telegrambot.sync.ActionSynchronizer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


/**
 * Created on 07.06.2024.
 *
 * A strategy that allows handlers to be executed for each user independently of other users.
 * But actions of the same user in the same chat will be executed strictly sequentially.
 * At the same time actions of the same user in different chats will be executed independently.
 *
 * @author Denis Matytsin
 */
internal class HandlerInvocationSmartSyncStrategy(
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob()),
) : HandlerInvocationStrategy {
    private val synchronizer = ActionSynchronizer<SynchronizerArgument>(scope)

    override suspend fun invokeHandler(chatId: Long, fromId: Long, action: suspend () -> Unit): Unit {
        synchronizer.parallelisedBy(arg(chatId, fromId), action)
    }

    private fun arg(chatId: Long, fromId: Long) = SynchronizerArgument(chatId, fromId)

    private data class SynchronizerArgument(
        val chatId: Long,
        val fromId: Long,
    )
}