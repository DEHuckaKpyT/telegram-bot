package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * This object represents one shipping option.
 *
 * @see [ShippingOption] (https://core.telegram.org/bots/api/#shippingoption)
 *
 * @author KScript
 *
 * @param id Shipping option identifier
 * @param title Option title
 * @param prices List of price portions
 */
public data class ShippingOption(
    /**
     * Shipping option identifier
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: String,
    /**
     * Option title
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String,
    /**
     * List of price portions
     */
    @get:JsonProperty("prices")
    @param:JsonProperty("prices")
    public val prices: List<LabeledPrice>,
)
