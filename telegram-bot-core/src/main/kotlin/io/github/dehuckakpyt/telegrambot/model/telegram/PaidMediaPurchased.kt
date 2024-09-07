package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * This object contains information about a paid media purchase.
 *
 * @see [PaidMediaPurchased] (https://core.telegram.org/bots/api/#paidmediapurchased)
 *
 * @author KScript
 *
 * @param from User who purchased the media
 * @param paidMediaPayload Bot-specified paid media payload
 */
public data class PaidMediaPurchased(
    /**
     * User who purchased the media
     */
    @get:JsonProperty("from")
    @param:JsonProperty("from")
    public val from: User,
    /**
     * Bot-specified paid media payload
     */
    @get:JsonProperty("paid_media_payload")
    @param:JsonProperty("paid_media_payload")
    public val paidMediaPayload: String,
)
