package io.github.dehuckakpyt.telegrambot.ext.strategy.invocation

import io.github.dehuckakpyt.telegrambot.strategy.invocation.*
import kotlinx.coroutines.CoroutineScope


/**
 * Created on 07.06.2024.
 *
 * @author Denis Matytsin
 */
@get:Deprecated("HandlerInvocationStrategy will be removed in future releases")
val HandlerInvocationStrategy.Companion.fullSync: HandlerInvocationStrategy get() = HandlerInvocationFullSyncStrategy()

@get:Deprecated("HandlerInvocationStrategy will be removed in future releases")
val HandlerInvocationStrategy.Companion.fullAsync: HandlerInvocationStrategy get() = HandlerInvocationFullAsyncStrategy()

@get:Deprecated("HandlerInvocationStrategy will be removed in future releases")
val HandlerInvocationStrategy.Companion.userSync: HandlerInvocationStrategy get() = HandlerInvocationUserSyncStrategy()

@get:Deprecated("HandlerInvocationStrategy will be removed in future releases")
val HandlerInvocationStrategy.Companion.chatSync: HandlerInvocationStrategy get() = HandlerInvocationChatSyncStrategy()

@get:Deprecated("HandlerInvocationStrategy will be removed in future releases")
val HandlerInvocationStrategy.Companion.smartSync: HandlerInvocationStrategy get() = HandlerInvocationSmartSyncStrategy()

@Deprecated("HandlerInvocationStrategy will be removed in future releases")
fun HandlerInvocationStrategy.Companion.fullAsync(scope: CoroutineScope): HandlerInvocationStrategy = HandlerInvocationFullAsyncStrategy(scope)

@Deprecated("HandlerInvocationStrategy will be removed in future releases")
fun HandlerInvocationStrategy.Companion.userSync(scope: CoroutineScope): HandlerInvocationStrategy = HandlerInvocationUserSyncStrategy(scope)

@Deprecated("HandlerInvocationStrategy will be removed in future releases")
fun HandlerInvocationStrategy.Companion.chatSync(scope: CoroutineScope): HandlerInvocationStrategy = HandlerInvocationChatSyncStrategy(scope)

@Deprecated("HandlerInvocationStrategy will be removed in future releases")
fun HandlerInvocationStrategy.Companion.smartSync(scope: CoroutineScope): HandlerInvocationStrategy = HandlerInvocationSmartSyncStrategy(scope)
