package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.String

/**
 * This object describes the state of a revenue withdrawal operation. Currently, it can be one of
 *
 * *
 * [RevenueWithdrawalStatePending](https://core.telegram.org/bots/api/#revenuewithdrawalstatepending)
 * *
 * [RevenueWithdrawalStateSucceeded](https://core.telegram.org/bots/api/#revenuewithdrawalstatesucceeded)
 * *
 * [RevenueWithdrawalStateFailed](https://core.telegram.org/bots/api/#revenuewithdrawalstatefailed)
 *
 * @see [RevenueWithdrawalState] (https://core.telegram.org/bots/api/#revenuewithdrawalstate)
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
    JsonSubTypes.Type(value = RevenueWithdrawalStatePending::class, name = "pending"),
    JsonSubTypes.Type(value = RevenueWithdrawalStateSucceeded::class, name = "succeeded"),
    JsonSubTypes.Type(value = RevenueWithdrawalStateFailed::class, name = "failed"),
)
public sealed interface RevenueWithdrawalState {
    public val type: String
}
