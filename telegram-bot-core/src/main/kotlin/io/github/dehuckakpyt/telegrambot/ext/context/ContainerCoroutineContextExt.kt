package io.github.dehuckakpyt.telegrambot.ext.context

import io.github.dehuckakpyt.telegrambot.container.Container
import io.github.dehuckakpyt.telegrambot.context.container.ContainerCoroutineContext
import kotlinx.coroutines.currentCoroutineContext
import org.jetbrains.annotations.ApiStatus.Experimental


/**
 * Created on 01.05.2024.
 *
 * @author Denis Matytsin
 */
@Experimental
public suspend fun currentContainerContextOrNull(): Container? = currentCoroutineContext()[ContainerCoroutineContext.Key]?.container

@Experimental
public suspend fun currentContainerContext(): Container = currentContainerContextOrNull() ?: throw IllegalStateException("Failed to receive container from coroutine context. Maybe you call \"currentContainerContext()\" from external coroutine context?")