package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * Contains information about the affiliate that received a commission via this transaction.
 *
 * @see [AffiliateInfo] (https://core.telegram.org/bots/api/#affiliateinfo)
 *
 * @author KScript
 *
 * @param affiliateUser *Optional*. The bot or the user that received an affiliate commission if it
 * was received by a bot or a user
 * @param affiliateChat *Optional*. The chat that received an affiliate commission if it was
 * received by a chat
 * @param commissionPerMille The number of Telegram Stars received by the affiliate for each 1000
 * Telegram Stars received by the bot from referred users
 * @param amount Integer amount of Telegram Stars received by the affiliate from the transaction,
 * rounded to 0; can be negative for refunds
 * @param nanostarAmount *Optional*. The number of 1/1000000000 shares of Telegram Stars received by
 * the affiliate; from -999999999 to 999999999; can be negative for refunds
 */
public data class AffiliateInfo(
    /**
     * *Optional*. The bot or the user that received an affiliate commission if it was received by a
     * bot or a user
     */
    @get:JsonProperty("affiliate_user")
    @param:JsonProperty("affiliate_user")
    public val affiliateUser: User? = null,
    /**
     * *Optional*. The chat that received an affiliate commission if it was received by a chat
     */
    @get:JsonProperty("affiliate_chat")
    @param:JsonProperty("affiliate_chat")
    public val affiliateChat: Chat? = null,
    /**
     * The number of Telegram Stars received by the affiliate for each 1000 Telegram Stars received
     * by the bot from referred users
     */
    @get:JsonProperty("commission_per_mille")
    @param:JsonProperty("commission_per_mille")
    public val commissionPerMille: Int,
    /**
     * Integer amount of Telegram Stars received by the affiliate from the transaction, rounded to
     * 0; can be negative for refunds
     */
    @get:JsonProperty("amount")
    @param:JsonProperty("amount")
    public val amount: Int,
    /**
     * *Optional*. The number of 1/1000000000 shares of Telegram Stars received by the affiliate;
     * from -999999999 to 999999999; can be negative for refunds
     */
    @get:JsonProperty("nanostar_amount")
    @param:JsonProperty("nanostar_amount")
    public val nanostarAmount: Int? = null,
)
