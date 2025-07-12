package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Contains the list of gifts received and owned by a user or a chat.
 *
 * @see [OwnedGifts] (https://core.telegram.org/bots/api/#ownedgifts)
 *
 * @author KScript
 *
 * @param totalCount The total number of gifts owned by the user or the chat
 * @param gifts The list of gifts
 * @param nextOffset *Optional*. Offset for the next request. If empty, then there are no more
 * results
 */
public data class OwnedGifts(
    /**
     * The total number of gifts owned by the user or the chat
     */
    @get:JsonProperty("total_count")
    @param:JsonProperty("total_count")
    public val totalCount: Int,
    /**
     * The list of gifts
     */
    @get:JsonProperty("gifts")
    @param:JsonProperty("gifts")
    public val gifts: List<OwnedGift>,
    /**
     * *Optional*. Offset for the next request. If empty, then there are no more results
     */
    @get:JsonProperty("next_offset")
    @param:JsonProperty("next_offset")
    public val nextOffset: String? = null,
)
