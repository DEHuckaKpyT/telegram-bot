package io.github.dehuckakpyt.telegrambot.ext.java.lang

import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.lang.reflect.ParameterizedType


/**
 * @author Denis Matytsin
 */
private val lookup = MethodHandles.lookup()

@Suppress("UNCHECKED_CAST")
public fun <T : Any, R> Class<T>.firstGenericClass(): Class<R> {
    return (this.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<R>
}

public fun <T : Any> Class<T>.getHandleOfEmptyDeclaredConstructor(): MethodHandle {
    val constructor = this.getDeclaredConstructor()

    return lookup.unreflectConstructor(constructor)
}