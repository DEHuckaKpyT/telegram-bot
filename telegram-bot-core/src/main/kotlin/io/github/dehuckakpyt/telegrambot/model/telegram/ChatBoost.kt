package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * This object contains information about a chat boost.
 *
 * @see [ChatBoost] (https://core.telegram.org/bots/api/#chatboost)
 *
 * @author KScript
 *
 * @param boostId Unique identifier of the boost
 * @param addDate Point in time (Unix timestamp) when the chat was boosted
 * @param expirationDate Point in time (Unix timestamp) when the boost will automatically expire,
 * unless the booster's Telegram Premium subscription is prolonged
 * @param source Source of the added boost
 */
public data class ChatBoost(
    /**
     * Unique identifier of the boost
     */
    @get:JsonProperty("boost_id")
    @param:JsonProperty("boost_id")
    public val boostId: String,
    /**
     * Point in time (Unix timestamp) when the chat was boosted
     */
    @get:JsonProperty("add_date")
    @param:JsonProperty("add_date")
    public val addDate: Long,
    /**
     * Point in time (Unix timestamp) when the boost will automatically expire, unless the booster's
     * Telegram Premium subscription is prolonged
     */
    @get:JsonProperty("expiration_date")
    @param:JsonProperty("expiration_date")
    public val expirationDate: Long,
    /**
     * Source of the added boost
     */
    @get:JsonProperty("source")
    @param:JsonProperty("source")
    public val source: ChatBoostSource,
)
