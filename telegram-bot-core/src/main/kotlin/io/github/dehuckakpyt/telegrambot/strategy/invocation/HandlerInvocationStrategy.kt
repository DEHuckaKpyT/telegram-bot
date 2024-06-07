package io.github.dehuckakpyt.telegrambot.strategy.invocation

import org.jetbrains.annotations.ApiStatus.Experimental


/**
 * Created on 07.06.2024.
 *
 * @author Denis Matytsin
 */
@Experimental
public interface HandlerInvocationStrategy {
    public suspend fun invokeHandler(chatId: Long, fromId: Long, action: suspend () -> Unit): Unit

    companion object
}