package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.String

/**
 * This object describes the source of a transaction, or its recipient for outgoing transactions.
 * Currently, it can be one of
 *
 * * [TransactionPartnerUser](https://core.telegram.org/bots/api/#transactionpartneruser)
 * *
 * [TransactionPartnerAffiliateProgram](https://core.telegram.org/bots/api/#transactionpartneraffiliateprogram)
 * * [TransactionPartnerFragment](https://core.telegram.org/bots/api/#transactionpartnerfragment)
 * *
 * [TransactionPartnerTelegramAds](https://core.telegram.org/bots/api/#transactionpartnertelegramads)
 * *
 * [TransactionPartnerTelegramApi](https://core.telegram.org/bots/api/#transactionpartnertelegramapi)
 * * [TransactionPartnerOther](https://core.telegram.org/bots/api/#transactionpartnerother)
 *
 * @see [TransactionPartner] (https://core.telegram.org/bots/api/#transactionpartner)
 *
 * @author KScript
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true,
)
@JsonSubTypes(
    JsonSubTypes.Type(value = TransactionPartnerUser::class, name = "user"),
    JsonSubTypes.Type(value = TransactionPartnerAffiliateProgram::class, name =
            "affiliate_program"),
    JsonSubTypes.Type(value = TransactionPartnerFragment::class, name = "fragment"),
    JsonSubTypes.Type(value = TransactionPartnerTelegramAds::class, name = "telegram_ads"),
    JsonSubTypes.Type(value = TransactionPartnerTelegramApi::class, name = "telegram_api"),
    JsonSubTypes.Type(value = TransactionPartnerOther::class, name = "other"),
)
public sealed interface TransactionPartner {
    public val type: String
}
