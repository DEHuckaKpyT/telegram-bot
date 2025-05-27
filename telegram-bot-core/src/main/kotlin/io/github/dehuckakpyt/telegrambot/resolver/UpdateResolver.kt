package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.model.telegram.Update


/**
 * Created on 17.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
public interface UpdateResolver {
    public suspend fun processUpdate(update: Update): Unit
    public val allowedUpdates: Set<String>
}