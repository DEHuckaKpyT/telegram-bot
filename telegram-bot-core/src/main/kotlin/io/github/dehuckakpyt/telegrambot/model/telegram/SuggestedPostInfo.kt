package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * Contains information about a suggested post.
 *
 * @see [SuggestedPostInfo] (https://core.telegram.org/bots/api/#suggestedpostinfo)
 *
 * @author KScript
 *
 * @param state State of the suggested post. Currently, it can be one of “pending”, “approved”,
 * “declined”.
 * @param price *Optional*. Proposed price of the post. If the field is omitted, then the post is
 * unpaid.
 * @param sendDate *Optional*. Proposed send date of the post. If the field is omitted, then the
 * post can be published at any time within 30 days at the sole discretion of the user or administrator
 * who approves it.
 */
public data class SuggestedPostInfo(
    /**
     * State of the suggested post. Currently, it can be one of “pending”, “approved”, “declined”.
     */
    @get:JsonProperty("state")
    @param:JsonProperty("state")
    public val state: String,
    /**
     * *Optional*. Proposed price of the post. If the field is omitted, then the post is unpaid.
     */
    @get:JsonProperty("price")
    @param:JsonProperty("price")
    public val price: SuggestedPostPrice? = null,
    /**
     * *Optional*. Proposed send date of the post. If the field is omitted, then the post can be
     * published at any time within 30 days at the sole discretion of the user or administrator who
     * approves it.
     */
    @get:JsonProperty("send_date")
    @param:JsonProperty("send_date")
    public val sendDate: Long? = null,
)
