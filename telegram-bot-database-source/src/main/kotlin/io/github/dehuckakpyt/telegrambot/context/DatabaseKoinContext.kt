package io.github.dehuckakpyt.telegrambot.context

import org.koin.core.module.Module
import org.koin.dsl.koinApplication


/**
 * Created on 07.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal object DatabaseKoinContext {

    private val koinApp = koinApplication()

    fun loadDatabaseKoinModules(vararg modules: Module) = synchronized(this) {
        koinApp.modules(modules.toList())
    }

    val koin = koinApp.koin
}