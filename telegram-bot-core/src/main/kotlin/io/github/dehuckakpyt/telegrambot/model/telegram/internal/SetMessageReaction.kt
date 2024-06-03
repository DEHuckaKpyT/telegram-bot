package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.ReactionType
import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable

/**
 * Created on 03.06.2024.
 *
 * @author KScript
 */
internal data class SetMessageReaction(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("message_id")
    public val messageId: Long,
    @get:JsonProperty("reaction")
    public val reaction: Iterable<ReactionType>? = null,
    @get:JsonProperty("is_big")
    public val isBig: Boolean? = null,
)
