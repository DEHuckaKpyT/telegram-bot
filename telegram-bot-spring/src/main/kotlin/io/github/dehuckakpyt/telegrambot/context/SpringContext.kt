package io.github.dehuckakpyt.telegrambot.context

import org.springframework.context.ApplicationContext


/**
 * Created on 29.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
//TODO remove this bad code
@Deprecated("Will be remove in future release.")
object SpringContext {
    lateinit var context: ApplicationContext

//    inline fun <reified T : Any> inject(name: String? = null): ReadOnlyProperty<Any?, T> = inject(T::class.java, name)
//    fun <T : Any> inject(type: Class<T>, name: String? = null): ReadOnlyProperty<Any?, T> = Inject(type, name)
//
//    private class Inject<T : Any>(
//        private val type: Class<T>,
//        private val name: String?,
//    ) : ReadOnlyProperty<Any?, T> {
//
//        private val value by lazy { if (name == null) context.getBean(type) else context.getBean(name, type) }
//        override operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value
//    }

    inline fun <reified T : Any> autowired(name: String? = null): T = autowired(T::class.java, name)
    fun <T : Any> autowired(type: Class<T>, name: String? = null): T =
        if (name == null) context.getBean(type) else context.getBean(name, type)
}