package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long

/**
 * Contains parameters of a post that is being suggested by the bot.
 *
 * @see [SuggestedPostParameters] (https://core.telegram.org/bots/api/#suggestedpostparameters)
 *
 * @author KScript
 *
 * @param price *Optional*. Proposed price for the post. If the field is omitted, then the post is
 * unpaid.
 * @param sendDate *Optional*. Proposed send date of the post. If specified, then the date must be
 * between 300 second and 2678400 seconds (30 days) in the future. If the field is omitted, then the
 * post can be published at any time within 30 days at the sole discretion of the user who approves it.
 */
public data class SuggestedPostParameters(
    /**
     * *Optional*. Proposed price for the post. If the field is omitted, then the post is unpaid.
     */
    @get:JsonProperty("price")
    @param:JsonProperty("price")
    public val price: SuggestedPostPrice? = null,
    /**
     * *Optional*. Proposed send date of the post. If specified, then the date must be between 300
     * second and 2678400 seconds (30 days) in the future. If the field is omitted, then the post can
     * be published at any time within 30 days at the sole discretion of the user who approves it.
     */
    @get:JsonProperty("send_date")
    @param:JsonProperty("send_date")
    public val sendDate: Long? = null,
)
