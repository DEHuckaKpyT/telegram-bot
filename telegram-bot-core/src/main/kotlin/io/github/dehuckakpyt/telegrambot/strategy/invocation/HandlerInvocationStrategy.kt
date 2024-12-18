package io.github.dehuckakpyt.telegrambot.strategy.invocation


/**
 * Created on 07.06.2024.
 *
 * @author Denis Matytsin
 */
@Deprecated("HandlerInvocationStrategy will be removed in future releases")
public interface HandlerInvocationStrategy {
    public suspend fun invokeHandler(chatId: Long, fromId: Long, action: suspend () -> Unit): Unit

    companion object
}