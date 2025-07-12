package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String

/**
 * @author KScript
 */
internal data class UpgradeGift(
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String,
    @get:JsonProperty("owned_gift_id")
    public val ownedGiftId: String,
    @get:JsonProperty("keep_original_details")
    public val keepOriginalDetails: Boolean? = null,
    @get:JsonProperty("star_count")
    public val starCount: Int? = null,
)
