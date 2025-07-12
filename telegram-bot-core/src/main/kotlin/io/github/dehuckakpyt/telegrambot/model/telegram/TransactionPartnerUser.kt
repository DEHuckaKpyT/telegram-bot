package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Describes a transaction with a user.
 *
 * @see [TransactionPartnerUser] (https://core.telegram.org/bots/api/#transactionpartneruser)
 *
 * @author KScript
 *
 * @param type Type of the transaction partner, always “user”
 * @param transactionType Type of the transaction, currently one of “invoice_payment” for payments
 * via invoices, “paid_media_payment” for payments for paid media, “gift_purchase” for gifts sent by
 * the bot, “premium_purchase” for Telegram Premium subscriptions gifted by the bot,
 * “business_account_transfer” for direct transfers from managed business accounts
 * @param user Information about the user
 * @param affiliate *Optional*. Information about the affiliate that received a commission via this
 * transaction. Can be available only for “invoice_payment” and “paid_media_payment” transactions.
 * @param invoicePayload *Optional*. Bot-specified invoice payload. Can be available only for
 * “invoice_payment” transactions.
 * @param subscriptionPeriod *Optional*. The duration of the paid subscription. Can be available
 * only for “invoice_payment” transactions.
 * @param paidMedia *Optional*. Information about the paid media bought by the user; for
 * “paid_media_payment” transactions only
 * @param paidMediaPayload *Optional*. Bot-specified paid media payload. Can be available only for
 * “paid_media_payment” transactions.
 * @param gift *Optional*. The gift sent to the user by the bot; for “gift_purchase” transactions
 * only
 * @param premiumSubscriptionDuration *Optional*. Number of months the gifted Telegram Premium
 * subscription will be active for; for “premium_purchase” transactions only
 */
public data class TransactionPartnerUser(
    /**
     * Type of the transaction partner, always “user”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Type of the transaction, currently one of “invoice_payment” for payments via invoices,
     * “paid_media_payment” for payments for paid media, “gift_purchase” for gifts sent by the bot,
     * “premium_purchase” for Telegram Premium subscriptions gifted by the bot,
     * “business_account_transfer” for direct transfers from managed business accounts
     */
    @get:JsonProperty("transaction_type")
    @param:JsonProperty("transaction_type")
    public val transactionType: String,
    /**
     * Information about the user
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    public val user: User,
    /**
     * *Optional*. Information about the affiliate that received a commission via this transaction.
     * Can be available only for “invoice_payment” and “paid_media_payment” transactions.
     */
    @get:JsonProperty("affiliate")
    @param:JsonProperty("affiliate")
    public val affiliate: AffiliateInfo? = null,
    /**
     * *Optional*. Bot-specified invoice payload. Can be available only for “invoice_payment”
     * transactions.
     */
    @get:JsonProperty("invoice_payload")
    @param:JsonProperty("invoice_payload")
    public val invoicePayload: String? = null,
    /**
     * *Optional*. The duration of the paid subscription. Can be available only for
     * “invoice_payment” transactions.
     */
    @get:JsonProperty("subscription_period")
    @param:JsonProperty("subscription_period")
    public val subscriptionPeriod: Int? = null,
    /**
     * *Optional*. Information about the paid media bought by the user; for “paid_media_payment”
     * transactions only
     */
    @get:JsonProperty("paid_media")
    @param:JsonProperty("paid_media")
    public val paidMedia: List<PaidMedia>? = null,
    /**
     * *Optional*. Bot-specified paid media payload. Can be available only for “paid_media_payment”
     * transactions.
     */
    @get:JsonProperty("paid_media_payload")
    @param:JsonProperty("paid_media_payload")
    public val paidMediaPayload: String? = null,
    /**
     * *Optional*. The gift sent to the user by the bot; for “gift_purchase” transactions only
     */
    @get:JsonProperty("gift")
    @param:JsonProperty("gift")
    public val gift: Gift? = null,
    /**
     * *Optional*. Number of months the gifted Telegram Premium subscription will be active for; for
     * “premium_purchase” transactions only
     */
    @get:JsonProperty("premium_subscription_duration")
    @param:JsonProperty("premium_subscription_duration")
    public val premiumSubscriptionDuration: Int? = null,
) : TransactionPartner
