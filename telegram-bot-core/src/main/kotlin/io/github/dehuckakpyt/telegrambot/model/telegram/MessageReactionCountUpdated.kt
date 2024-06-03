package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.collections.List

/**
 * Created on 03.06.2024.
 *
 * This object represents reaction changes on a message with anonymous reactions.
 *
 * @see [MessageReactionCountUpdated]
 * (https://core.telegram.org/bots/api/#messagereactioncountupdated)
 *
 * @author KScript
 *
 * @param chat The chat containing the message
 * @param messageId Unique message identifier inside the chat
 * @param date Date of the change in Unix time
 * @param reactions List of reactions that are present on the message
 */
public data class MessageReactionCountUpdated(
    /**
     * The chat containing the message
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    public val chat: Chat,
    /**
     * Unique message identifier inside the chat
     */
    @get:JsonProperty("message_id")
    @param:JsonProperty("message_id")
    public val messageId: Long,
    /**
     * Date of the change in Unix time
     */
    @get:JsonProperty("date")
    @param:JsonProperty("date")
    public val date: Long,
    /**
     * List of reactions that are present on the message
     */
    @get:JsonProperty("reactions")
    @param:JsonProperty("reactions")
    public val reactions: List<ReactionCount>,
)
