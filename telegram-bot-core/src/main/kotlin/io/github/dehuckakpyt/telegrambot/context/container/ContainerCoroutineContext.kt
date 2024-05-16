package io.github.dehuckakpyt.telegrambot.context.container

import io.github.dehuckakpyt.telegrambot.container.Container
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext


/**
 * Created on 01.05.2024.
 *
 * @author Denis Matytsin
 */
internal class ContainerCoroutineContext(
    internal val container: Container,
) : AbstractCoroutineContextElement(Key) {

    internal companion object Key : CoroutineContext.Key<ContainerCoroutineContext>
}