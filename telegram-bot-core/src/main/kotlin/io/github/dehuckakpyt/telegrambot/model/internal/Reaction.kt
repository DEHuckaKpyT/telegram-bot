package io.github.dehuckakpyt.telegrambot.model.internal

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.type.ReactionType


/**
 * Created on 11.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
internal data class SetMessageReaction(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("message_id") val messageId: Long,
    @get:JsonProperty("reaction") val reaction: Iterable<ReactionType>? = null,
    @get:JsonProperty("is_big") val isBig: Boolean? = null,
)