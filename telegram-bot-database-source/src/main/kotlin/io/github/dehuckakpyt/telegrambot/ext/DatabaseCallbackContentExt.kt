package io.github.dehuckakpyt.telegrambot.ext

import io.github.dehuckakpyt.telegrambot.model.CallbackContent
import io.github.dehuckakpyt.telegrambot.model.CallbackContentImpl
import io.github.dehuckakpyt.telegrambot.model.DatabaseCallbackContent


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