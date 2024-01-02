package io.github.dehuckakpyt.telegrambot.source.callback

import io.github.dehuckakpyt.telegrambot.model.source.CallbackContent
import java.util.*


/**
 * Created on 23.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface CallbackContentSource {
    suspend fun save(chatId: Long, fromId: Long, content: String): CallbackContent
    suspend fun get(callbackId: UUID): CallbackContent

    companion object
}