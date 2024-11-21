package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.collections.List

/**
 * This object represent a list of gifts.
 *
 * @see [Gifts] (https://core.telegram.org/bots/api/#gifts)
 *
 * @author KScript
 *
 * @param gifts The list of gifts
 */
public data class Gifts(
    /**
     * The list of gifts
     */
    @get:JsonProperty("gifts")
    @param:JsonProperty("gifts")
    public val gifts: List<Gift>,
)
