package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * This object contains information about an incoming shipping query.
 *
 * @see [ShippingQuery] (https://core.telegram.org/bots/api/#shippingquery)
 *
 * @author KScript
 *
 * @param id Unique query identifier
 * @param from User who sent the query
 * @param invoicePayload Bot-specified invoice payload
 * @param shippingAddress User specified shipping address
 */
public data class ShippingQuery(
    /**
     * Unique query identifier
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: String,
    /**
     * User who sent the query
     */
    @get:JsonProperty("from")
    @param:JsonProperty("from")
    public val from: User,
    /**
     * Bot-specified invoice payload
     */
    @get:JsonProperty("invoice_payload")
    @param:JsonProperty("invoice_payload")
    public val invoicePayload: String,
    /**
     * User specified shipping address
     */
    @get:JsonProperty("shipping_address")
    @param:JsonProperty("shipping_address")
    public val shippingAddress: ShippingAddress,
)
