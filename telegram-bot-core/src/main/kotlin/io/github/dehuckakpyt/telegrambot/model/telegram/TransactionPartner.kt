package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.String

/**
 * This object describes the source of a transaction, or its recipient for outgoing transactions.
 * Currently, it can be one of
 *
 * * [TransactionPartnerFragment](https://core.telegram.org/bots/api/#transactionpartnerfragment)
 * * [TransactionPartnerUser](https://core.telegram.org/bots/api/#transactionpartneruser)
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
    JsonSubTypes.Type(value = TransactionPartnerFragment::class, name = "fragment"),
    JsonSubTypes.Type(value = TransactionPartnerUser::class, name = "user"),
    JsonSubTypes.Type(value = TransactionPartnerOther::class, name = "other"),
)
public sealed interface TransactionPartner {
    public val type: String
}
