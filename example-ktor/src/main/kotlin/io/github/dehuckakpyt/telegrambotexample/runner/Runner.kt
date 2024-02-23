package io.github.dehuckakpyt.telegrambotexample.runner

import org.koin.core.component.KoinComponent


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface Runner : KoinComponent {
    suspend fun execute()
}