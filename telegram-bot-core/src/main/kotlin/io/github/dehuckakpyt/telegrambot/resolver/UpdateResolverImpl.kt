package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.model.type.Update


/**
 * Created on 08.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class UpdateResolverImpl(
    private val dialogUpdateResolver: DialogUpdateResolver,
    private val eventUpdateResolver: EventUpdateResolver,
) : UpdateResolver {

    override suspend fun processUpdate(update: Update): Unit = with(update) {
        eventUpdateResolver.processUpdate(this)

        when {
            message != null -> dialogUpdateResolver.processMessage(message)
            callbackQuery != null -> dialogUpdateResolver.processCallback(callbackQuery)
        }
    }
}