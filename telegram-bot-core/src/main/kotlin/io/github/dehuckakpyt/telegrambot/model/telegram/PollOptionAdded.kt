package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * Describes a service message about an option added to a poll.
 *
 * @see [PollOptionAdded] (https://core.telegram.org/bots/api/#polloptionadded)
 *
 * @author KScript
 *
 * @param pollMessage *Optional*. Message containing the poll to which the option was added, if
 * known. Note that the [Message](https://core.telegram.org/bots/api/#message) object in this field
 * will not contain the *reply_to_message* field even if it itself is a reply.
 * @param optionPersistentId Unique identifier of the added option
 * @param optionText Option text
 * @param optionTextEntities *Optional*. Special entities that appear in the *option_text*
 */
public data class PollOptionAdded(
    /**
     * *Optional*. Message containing the poll to which the option was added, if known. Note that
     * the [Message](https://core.telegram.org/bots/api/#message) object in this field will not contain
     * the *reply_to_message* field even if it itself is a reply.
     */
    @get:JsonProperty("poll_message")
    @param:JsonProperty("poll_message")
    public val pollMessage: MaybeInaccessibleMessage? = null,
    /**
     * Unique identifier of the added option
     */
    @get:JsonProperty("option_persistent_id")
    @param:JsonProperty("option_persistent_id")
    public val optionPersistentId: String,
    /**
     * Option text
     */
    @get:JsonProperty("option_text")
    @param:JsonProperty("option_text")
    public val optionText: String,
    /**
     * *Optional*. Special entities that appear in the *option_text*
     */
    @get:JsonProperty("option_text_entities")
    @param:JsonProperty("option_text_entities")
    public val optionTextEntities: List<MessageEntity>? = null,
)
