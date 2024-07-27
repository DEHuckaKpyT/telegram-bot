package io.github.dehuckakpyt.telegrambot.model.source

import java.util.*


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CallbackContentImpl(
    override val callbackId: UUID,
    override val chatId: Long,
    override val fromId: Long,
    override val content: String,
) : CallbackContent {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CallbackContentImpl

        return callbackId == other.callbackId
    }

    override fun hashCode(): Int {
        return callbackId.hashCode()
    }
}