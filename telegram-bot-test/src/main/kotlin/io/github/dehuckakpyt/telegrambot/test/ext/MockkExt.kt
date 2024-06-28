package io.github.dehuckakpyt.telegrambot.test.ext

import io.mockk.MockKGateway
import kotlin.reflect.KClass


/**
 * Created on 19.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */

public fun <T : Any> anyConstructed(clazz: KClass<T>): T =
    MockKGateway.implementation().constructorMockFactory.mockPlaceholder(clazz)
