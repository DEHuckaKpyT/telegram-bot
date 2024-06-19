package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long
import kotlin.String

/**
 * The boost was obtained by the creation of a Telegram Premium giveaway. This boosts the chat 4
 * times for the duration of the corresponding Telegram Premium subscription.
 *
 * @see [ChatBoostSourceGiveaway] (https://core.telegram.org/bots/api/#chatboostsourcegiveaway)
 *
 * @author KScript
 *
 * @param source Source of the boost, always “giveaway”
 * @param giveawayMessageId Identifier of a message in the chat with the giveaway; the message could
 * have been deleted already. May be 0 if the message isn't sent yet.
 * @param user *Optional*. User that won the prize in the giveaway if any
 * @param isUnclaimed *Optional*. True, if the giveaway was completed, but there was no user to win
 * the prize
 */
public data class ChatBoostSourceGiveaway(
    /**
     * Source of the boost, always “giveaway”
     */
    @get:JsonProperty("source")
    @param:JsonProperty("source")
    override val source: String,
    /**
     * Identifier of a message in the chat with the giveaway; the message could have been deleted
     * already. May be 0 if the message isn't sent yet.
     */
    @get:JsonProperty("giveaway_message_id")
    @param:JsonProperty("giveaway_message_id")
    public val giveawayMessageId: Long,
    /**
     * *Optional*. User that won the prize in the giveaway if any
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    override val user: User? = null,
    /**
     * *Optional*. True, if the giveaway was completed, but there was no user to win the prize
     */
    @get:JsonProperty("is_unclaimed")
    @param:JsonProperty("is_unclaimed")
    public val isUnclaimed: Boolean? = null,
) : ChatBoostSource
