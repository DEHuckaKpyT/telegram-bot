package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * Describes a transaction with payment for [paid
 * broadcasting](https://core.telegram.org/bots/api/#paid-broadcasts).
 *
 * @see [TransactionPartnerTelegramApi]
 * (https://core.telegram.org/bots/api/#transactionpartnertelegramapi)
 *
 * @author KScript
 *
 * @param type Type of the transaction partner, always “telegram_api”
 * @param requestCount The number of successful requests that exceeded regular limits and were
 * therefore billed
 */
public data class TransactionPartnerTelegramApi(
    /**
     * Type of the transaction partner, always “telegram_api”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The number of successful requests that exceeded regular limits and were therefore billed
     */
    @get:JsonProperty("request_count")
    @param:JsonProperty("request_count")
    public val requestCount: Int,
) : TransactionPartner
