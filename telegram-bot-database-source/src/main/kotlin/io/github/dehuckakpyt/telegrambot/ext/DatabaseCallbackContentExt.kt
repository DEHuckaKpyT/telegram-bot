package io.github.dehuckakpyt.telegrambot.ext

import io.github.dehuckakpyt.telegrambot.model.DatabaseCallbackContent
import io.github.dehuckakpyt.telegrambot.model.source.CallbackContent
import io.github.dehuckakpyt.telegrambot.model.source.CallbackContentImpl


/**
 * Created on 02.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal fun DatabaseCallbackContent.toImpl(): CallbackContent {
    return CallbackContentImpl(
        id = callbackId,
        chatId = chatId,
        fromId = fromId,
        content = content
    )
}