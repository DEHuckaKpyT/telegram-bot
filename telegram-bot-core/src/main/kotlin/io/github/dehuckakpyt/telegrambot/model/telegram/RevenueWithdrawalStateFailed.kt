package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * The withdrawal failed and the transaction was refunded.
 *
 * @see [RevenueWithdrawalStateFailed]
 * (https://core.telegram.org/bots/api/#revenuewithdrawalstatefailed)
 *
 * @author KScript
 *
 * @param type Type of the state, always “failed”
 */
public data class RevenueWithdrawalStateFailed(
    /**
     * Type of the state, always “failed”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
) : RevenueWithdrawalState
