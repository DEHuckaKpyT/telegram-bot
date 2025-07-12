package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * Describes an amount of Telegram Stars.
 *
 * @see [StarAmount] (https://core.telegram.org/bots/api/#staramount)
 *
 * @author KScript
 *
 * @param amount Integer amount of Telegram Stars, rounded to 0; can be negative
 * @param nanostarAmount *Optional*. The number of 1/1000000000 shares of Telegram Stars;
 * from -999999999 to 999999999; can be negative if and only if *amount* is non-positive
 */
public data class StarAmount(
    /**
     * Integer amount of Telegram Stars, rounded to 0; can be negative
     */
    @get:JsonProperty("amount")
    @param:JsonProperty("amount")
    public val amount: Int,
    /**
     * *Optional*. The number of 1/1000000000 shares of Telegram Stars; from -999999999 to
     * 999999999; can be negative if and only if *amount* is non-positive
     */
    @get:JsonProperty("nanostar_amount")
    @param:JsonProperty("nanostar_amount")
    public val nanostarAmount: Int? = null,
)
