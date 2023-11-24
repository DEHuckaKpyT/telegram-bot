package io.github.dehuckakpyt.telegrambot.context

import org.koin.core.module.Module
import org.koin.dsl.koinApplication


/**
 * Created on 24.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal object InternalKoinContext {

    private val koinApp = koinApplication()

    fun loadInternalKoinModules(vararg modules: Module) = synchronized(this) {
        koinApp.modules(modules.toList())
    }

    val koin = koinApp.koin
}