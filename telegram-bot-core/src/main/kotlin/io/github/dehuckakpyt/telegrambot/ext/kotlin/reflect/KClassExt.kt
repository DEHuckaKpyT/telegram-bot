package io.github.dehuckakpyt.telegrambot.ext.kotlin.reflect

import io.github.dehuckakpyt.telegrambot.ext.java.lang.firstGenericClass
import kotlin.reflect.KClass


/**
 * @author Denis Matytsin
 */
public fun <T : Any, R> KClass<T>.firstGenericClass(): Class<R> = this.java.firstGenericClass()
