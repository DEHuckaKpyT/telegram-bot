package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * This object represents a boost removed from a chat.
 *
 * @see [ChatBoostRemoved] (https://core.telegram.org/bots/api/#chatboostremoved)
 *
 * @author KScript
 *
 * @param chat Chat which was boosted
 * @param boostId Unique identifier of the boost
 * @param removeDate Point in time (Unix timestamp) when the boost was removed
 * @param source Source of the removed boost
 */
public data class ChatBoostRemoved(
    /**
     * Chat which was boosted
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    public val chat: Chat,
    /**
     * Unique identifier of the boost
     */
    @get:JsonProperty("boost_id")
    @param:JsonProperty("boost_id")
    public val boostId: String,
    /**
     * Point in time (Unix timestamp) when the boost was removed
     */
    @get:JsonProperty("remove_date")
    @param:JsonProperty("remove_date")
    public val removeDate: Long,
    /**
     * Source of the removed boost
     */
    @get:JsonProperty("source")
    @param:JsonProperty("source")
    public val source: ChatBoostSource,
)
