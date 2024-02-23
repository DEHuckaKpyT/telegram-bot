package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.model.type.ReactionType


/**
 * Created on 11.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramReactionApi {

    suspend fun setMessageReaction(
        chatId: String,
        messageId: Long,
        reaction: Iterable<ReactionType>? = null,
        isBig: Boolean? = null,
    ): Boolean
}