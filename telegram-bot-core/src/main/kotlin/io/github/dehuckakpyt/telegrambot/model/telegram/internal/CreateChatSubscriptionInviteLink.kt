package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * @author KScript
 */
internal data class CreateChatSubscriptionInviteLink(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("subscription_period")
    public val subscriptionPeriod: Int,
    @get:JsonProperty("subscription_price")
    public val subscriptionPrice: Int,
    @get:JsonProperty("name")
    public val name: String? = null,
)
