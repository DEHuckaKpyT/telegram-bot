package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.model.telegram.Update


/**
 * Created on 17.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
interface UpdateResolver {
    suspend fun processUpdate(update: Update): Unit
    val allowedUpdates: Set<String>
}