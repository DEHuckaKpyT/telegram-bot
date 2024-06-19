package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.collections.List

/**
 * This object represents a change of a reaction on a message performed by a user.
 *
 * @see [MessageReactionUpdated] (https://core.telegram.org/bots/api/#messagereactionupdated)
 *
 * @author KScript
 *
 * @param chat The chat containing the message the user reacted to
 * @param messageId Unique identifier of the message inside the chat
 * @param user *Optional*. The user that changed the reaction, if the user isn't anonymous
 * @param actorChat *Optional*. The chat on behalf of which the reaction was changed, if the user is
 * anonymous
 * @param date Date of the change in Unix time
 * @param oldReaction Previous list of reaction types that were set by the user
 * @param newReaction New list of reaction types that have been set by the user
 */
public data class MessageReactionUpdated(
    /**
     * The chat containing the message the user reacted to
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    public val chat: Chat,
    /**
     * Unique identifier of the message inside the chat
     */
    @get:JsonProperty("message_id")
    @param:JsonProperty("message_id")
    public val messageId: Long,
    /**
     * *Optional*. The user that changed the reaction, if the user isn't anonymous
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    public val user: User? = null,
    /**
     * *Optional*. The chat on behalf of which the reaction was changed, if the user is anonymous
     */
    @get:JsonProperty("actor_chat")
    @param:JsonProperty("actor_chat")
    public val actorChat: Chat? = null,
    /**
     * Date of the change in Unix time
     */
    @get:JsonProperty("date")
    @param:JsonProperty("date")
    public val date: Long,
    /**
     * Previous list of reaction types that were set by the user
     */
    @get:JsonProperty("old_reaction")
    @param:JsonProperty("old_reaction")
    public val oldReaction: List<ReactionType>,
    /**
     * New list of reaction types that have been set by the user
     */
    @get:JsonProperty("new_reaction")
    @param:JsonProperty("new_reaction")
    public val newReaction: List<ReactionType>,
)
