package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.model.type.UpdateResponse


/**
 * Created on 08.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class UpdateResolver(
    private val dialogUpdateResolver: DialogUpdateResolver,
) {

    suspend fun processUpdate(update: UpdateResponse): Unit = with(update) {
        when {
            message != null -> dialogUpdateResolver.processMessage(message)
            callbackQuery != null -> dialogUpdateResolver.processCallback(callbackQuery)
        }
    }
}