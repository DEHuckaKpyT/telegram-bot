package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * Describes an inline message to be sent by a user of a Mini App.
 *
 * @see [PreparedInlineMessage] (https://core.telegram.org/bots/api/#preparedinlinemessage)
 *
 * @author KScript
 *
 * @param id Unique identifier of the prepared message
 * @param expirationDate Expiration date of the prepared message, in Unix time. Expired prepared
 * messages can no longer be used
 */
public data class PreparedInlineMessage(
    /**
     * Unique identifier of the prepared message
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: String,
    /**
     * Expiration date of the prepared message, in Unix time. Expired prepared messages can no
     * longer be used
     */
    @get:JsonProperty("expiration_date")
    @param:JsonProperty("expiration_date")
    public val expirationDate: Long,
)
