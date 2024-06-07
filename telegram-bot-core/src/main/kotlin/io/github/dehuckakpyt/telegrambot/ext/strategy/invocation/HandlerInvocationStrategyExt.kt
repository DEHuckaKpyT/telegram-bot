package io.github.dehuckakpyt.telegrambot.ext.strategy.invocation

import io.github.dehuckakpyt.telegrambot.strategy.invocation.*
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.annotations.ApiStatus.Experimental


/**
 * Created on 07.06.2024.
 *
 * @author Denis Matytsin
 */
@get:Experimental
val HandlerInvocationStrategy.Companion.fullSync: HandlerInvocationStrategy get() = HandlerInvocationFullSyncStrategy()
@get:Experimental
val HandlerInvocationStrategy.Companion.fullAsync: HandlerInvocationStrategy get() = HandlerInvocationFullAsyncStrategy()
@get:Experimental
val HandlerInvocationStrategy.Companion.userSync: HandlerInvocationStrategy get() = HandlerInvocationUserSyncStrategy()
@get:Experimental
val HandlerInvocationStrategy.Companion.chatSync: HandlerInvocationStrategy get() = HandlerInvocationChatSyncStrategy()
@get:Experimental
val HandlerInvocationStrategy.Companion.smartSync: HandlerInvocationStrategy get() = HandlerInvocationSmartSyncStrategy()

@Experimental
fun HandlerInvocationStrategy.Companion.fullAsync(scope: CoroutineScope): HandlerInvocationStrategy = HandlerInvocationFullAsyncStrategy(scope)
@Experimental
fun HandlerInvocationStrategy.Companion.userSync(scope: CoroutineScope): HandlerInvocationStrategy = HandlerInvocationUserSyncStrategy(scope)
@Experimental
fun HandlerInvocationStrategy.Companion.chatSync(scope: CoroutineScope): HandlerInvocationStrategy = HandlerInvocationChatSyncStrategy(scope)
@Experimental
fun HandlerInvocationStrategy.Companion.smartSync(scope: CoroutineScope): HandlerInvocationStrategy = HandlerInvocationSmartSyncStrategy(scope)