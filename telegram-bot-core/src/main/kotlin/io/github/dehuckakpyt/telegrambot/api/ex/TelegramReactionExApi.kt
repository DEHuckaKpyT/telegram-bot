package io.github.dehuckakpyt.telegrambot.api.ex

import io.github.dehuckakpyt.telegrambot.api.TelegramReactionApi
import io.github.dehuckakpyt.telegrambot.model.type.ReactionType


/**
 * Created on 11.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramReactionExApi : TelegramReactionApi {

    suspend fun setMessageReaction(
        chatId: Long,
        messageId: Long,
        reaction: Iterable<ReactionType>? = null,
        isBig: Boolean? = null,
    ): Boolean = setMessageReaction(
        chatId = chatId,
        messageId = messageId,
        reaction = reaction,
        isBig = isBig
    )
}