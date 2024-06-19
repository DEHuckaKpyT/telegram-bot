package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * The withdrawal succeeded.
 *
 * @see [RevenueWithdrawalStateSucceeded]
 * (https://core.telegram.org/bots/api/#revenuewithdrawalstatesucceeded)
 *
 * @author KScript
 *
 * @param type Type of the state, always “succeeded”
 * @param date Date the withdrawal was completed in Unix time
 * @param url An HTTPS URL that can be used to see transaction details
 */
public data class RevenueWithdrawalStateSucceeded(
    /**
     * Type of the state, always “succeeded”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Date the withdrawal was completed in Unix time
     */
    @get:JsonProperty("date")
    @param:JsonProperty("date")
    public val date: Long,
    /**
     * An HTTPS URL that can be used to see transaction details
     */
    @get:JsonProperty("url")
    @param:JsonProperty("url")
    public val url: String,
) : RevenueWithdrawalState
