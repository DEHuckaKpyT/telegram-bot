package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * The boost was obtained by the creation of Telegram Premium gift codes to boost a chat. Each such
 * code boosts the chat 4 times for the duration of the corresponding Telegram Premium subscription.
 *
 * @see [ChatBoostSourceGiftCode] (https://core.telegram.org/bots/api/#chatboostsourcegiftcode)
 *
 * @author KScript
 *
 * @param source Source of the boost, always “gift_code”
 * @param user User for which the gift code was created
 */
public data class ChatBoostSourceGiftCode(
    /**
     * Source of the boost, always “gift_code”
     */
    @get:JsonProperty("source")
    @param:JsonProperty("source")
    override val source: String,
    /**
     * User for which the gift code was created
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    override val user: User,
) : ChatBoostSource
