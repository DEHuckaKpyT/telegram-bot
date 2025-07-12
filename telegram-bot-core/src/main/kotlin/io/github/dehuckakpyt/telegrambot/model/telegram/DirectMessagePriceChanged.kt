package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int

/**
 * Describes a service message about a change in the price of direct messages sent to a channel
 * chat.
 *
 * @see [DirectMessagePriceChanged] (https://core.telegram.org/bots/api/#directmessagepricechanged)
 *
 * @author KScript
 *
 * @param areDirectMessagesEnabled *True*, if direct messages are enabled for the channel chat;
 * false otherwise
 * @param directMessageStarCount *Optional*. The new number of Telegram Stars that must be paid by
 * users for each direct message sent to the channel. Does not apply to users who have been exempted by
 * administrators. Defaults to 0.
 */
public data class DirectMessagePriceChanged(
    /**
     * *True*, if direct messages are enabled for the channel chat; false otherwise
     */
    @get:JsonProperty("are_direct_messages_enabled")
    @param:JsonProperty("are_direct_messages_enabled")
    public val areDirectMessagesEnabled: Boolean,
    /**
     * *Optional*. The new number of Telegram Stars that must be paid by users for each direct
     * message sent to the channel. Does not apply to users who have been exempted by administrators.
     * Defaults to 0.
     */
    @get:JsonProperty("direct_message_star_count")
    @param:JsonProperty("direct_message_star_count")
    public val directMessageStarCount: Int? = null,
)
