package io.github.dehuckakpyt.telegrambot.strategy.invocation


/**
 * Created on 07.06.2024.
 *
 * @author Denis Matytsin
 */
internal class HandlerInvocationFullSyncStrategy : HandlerInvocationStrategy {
    override suspend fun invokeHandler(chatId: Long, fromId: Long, action: suspend () -> Unit) = action()
}