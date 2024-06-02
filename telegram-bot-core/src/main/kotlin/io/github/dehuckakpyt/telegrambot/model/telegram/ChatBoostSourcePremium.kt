package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * The boost was obtained by subscribing to Telegram Premium or by gifting a Telegram Premium
 * subscription to another user.
 *
 * @see [ChatBoostSourcePremium] (https://core.telegram.org/bots/api/#chatboostsourcepremium)
 *
 * @author KScript
 *
 * @param source Source of the boost, always “premium”
 * @param user User that boosted the chat
 */
public data class ChatBoostSourcePremium(
    /**
     * Source of the boost, always “premium”
     */
    @get:JsonProperty("source")
    @param:JsonProperty("source")
    override val source: String,
    /**
     * User that boosted the chat
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    override val user: User,
) : ChatBoostSource
