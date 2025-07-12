package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class TransferGift(
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String,
    @get:JsonProperty("owned_gift_id")
    public val ownedGiftId: String,
    @get:JsonProperty("new_owner_chat_id")
    public val newOwnerChatId: Long,
    @get:JsonProperty("star_count")
    public val starCount: Int? = null,
)
