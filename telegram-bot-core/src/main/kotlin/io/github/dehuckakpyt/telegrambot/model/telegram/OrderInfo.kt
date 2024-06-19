package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * This object represents information about an order.
 *
 * @see [OrderInfo] (https://core.telegram.org/bots/api/#orderinfo)
 *
 * @author KScript
 *
 * @param name *Optional*. User name
 * @param phoneNumber *Optional*. User's phone number
 * @param email *Optional*. User email
 * @param shippingAddress *Optional*. User shipping address
 */
public data class OrderInfo(
    /**
     * *Optional*. User name
     */
    @get:JsonProperty("name")
    @param:JsonProperty("name")
    public val name: String? = null,
    /**
     * *Optional*. User's phone number
     */
    @get:JsonProperty("phone_number")
    @param:JsonProperty("phone_number")
    public val phoneNumber: String? = null,
    /**
     * *Optional*. User email
     */
    @get:JsonProperty("email")
    @param:JsonProperty("email")
    public val email: String? = null,
    /**
     * *Optional*. User shipping address
     */
    @get:JsonProperty("shipping_address")
    @param:JsonProperty("shipping_address")
    public val shippingAddress: ShippingAddress? = null,
)
