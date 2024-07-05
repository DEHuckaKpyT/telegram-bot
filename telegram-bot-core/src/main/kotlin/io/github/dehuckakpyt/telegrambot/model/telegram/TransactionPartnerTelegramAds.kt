package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes a withdrawal transaction to the Telegram Ads platform.
 *
 * @see [TransactionPartnerTelegramAds]
 * (https://core.telegram.org/bots/api/#transactionpartnertelegramads)
 *
 * @author KScript
 *
 * @param type Type of the transaction partner, always “telegram_ads”
 */
public data class TransactionPartnerTelegramAds(
    /**
     * Type of the transaction partner, always “telegram_ads”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
) : TransactionPartner
