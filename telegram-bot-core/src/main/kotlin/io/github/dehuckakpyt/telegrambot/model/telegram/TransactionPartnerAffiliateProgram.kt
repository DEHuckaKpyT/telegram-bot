package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * Describes the affiliate program that issued the affiliate commission received via this
 * transaction.
 *
 * @see [TransactionPartnerAffiliateProgram]
 * (https://core.telegram.org/bots/api/#transactionpartneraffiliateprogram)
 *
 * @author KScript
 *
 * @param type Type of the transaction partner, always “affiliate_program”
 * @param sponsorUser *Optional*. Information about the bot that sponsored the affiliate program
 * @param commissionPerMille The number of Telegram Stars received by the bot for each 1000 Telegram
 * Stars received by the affiliate program sponsor from referred users
 */
public data class TransactionPartnerAffiliateProgram(
    /**
     * Type of the transaction partner, always “affiliate_program”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * *Optional*. Information about the bot that sponsored the affiliate program
     */
    @get:JsonProperty("sponsor_user")
    @param:JsonProperty("sponsor_user")
    public val sponsorUser: User? = null,
    /**
     * The number of Telegram Stars received by the bot for each 1000 Telegram Stars received by the
     * affiliate program sponsor from referred users
     */
    @get:JsonProperty("commission_per_mille")
    @param:JsonProperty("commission_per_mille")
    public val commissionPerMille: Int,
) : TransactionPartner
