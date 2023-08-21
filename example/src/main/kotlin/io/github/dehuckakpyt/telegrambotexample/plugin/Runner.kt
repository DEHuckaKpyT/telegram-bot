package io.github.dehuckakpyt.telegrambotexample.plugin

import io.github.dehuckakpyt.telegrambotexample.runner.Runner
import io.ktor.server.application.*
import kotlinx.coroutines.runBlocking
import org.koin.ktor.ext.getKoin


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun Application.startRunners() {
    runBlocking {
        getKoin().getAll<Runner>().forEach {
            it.execute()
        }
    }
}