package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * The withdrawal is in progress.
 *
 * @see [RevenueWithdrawalStatePending]
 * (https://core.telegram.org/bots/api/#revenuewithdrawalstatepending)
 *
 * @author KScript
 *
 * @param type Type of the state, always “pending”
 */
public data class RevenueWithdrawalStatePending(
    /**
     * Type of the state, always “pending”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
) : RevenueWithdrawalState
