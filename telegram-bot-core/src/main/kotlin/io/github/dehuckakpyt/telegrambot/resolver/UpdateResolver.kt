package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.model.internal.AllowedUpdate
import io.github.dehuckakpyt.telegrambot.model.type.Update


/**
 * Created on 17.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
interface UpdateResolver {
    suspend fun processUpdate(update: Update): Unit
    val allowedUpdates: Set<AllowedUpdate>
}