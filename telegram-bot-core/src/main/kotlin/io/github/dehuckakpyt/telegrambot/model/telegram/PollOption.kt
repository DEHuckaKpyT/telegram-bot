package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * This object contains information about one answer option in a poll.
 *
 * @see [PollOption] (https://core.telegram.org/bots/api/#polloption)
 *
 * @author KScript
 *
 * @param persistentId Unique identifier of the option, persistent on option addition and deletion
 * @param text Option text, 1-100 characters
 * @param textEntities *Optional*. Special entities that appear in the option *text*. Currently,
 * only custom emoji entities are allowed in poll option texts
 * @param voterCount Number of users who voted for this option; may be 0 if unknown
 * @param addedByUser *Optional*. User who added the option; omitted if the option wasn't added by a
 * user after poll creation
 * @param addedByChat *Optional*. Chat that added the option; omitted if the option wasn't added by
 * a chat after poll creation
 * @param additionDate *Optional*. Point in time (Unix timestamp) when the option was added; omitted
 * if the option existed in the original poll
 */
public data class PollOption(
    /**
     * Unique identifier of the option, persistent on option addition and deletion
     */
    @get:JsonProperty("persistent_id")
    @param:JsonProperty("persistent_id")
    public val persistentId: String,
    /**
     * Option text, 1-100 characters
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: String,
    /**
     * *Optional*. Special entities that appear in the option *text*. Currently, only custom emoji
     * entities are allowed in poll option texts
     */
    @get:JsonProperty("text_entities")
    @param:JsonProperty("text_entities")
    public val textEntities: List<MessageEntity>? = null,
    /**
     * Number of users who voted for this option; may be 0 if unknown
     */
    @get:JsonProperty("voter_count")
    @param:JsonProperty("voter_count")
    public val voterCount: Int,
    /**
     * *Optional*. User who added the option; omitted if the option wasn't added by a user after
     * poll creation
     */
    @get:JsonProperty("added_by_user")
    @param:JsonProperty("added_by_user")
    public val addedByUser: User? = null,
    /**
     * *Optional*. Chat that added the option; omitted if the option wasn't added by a chat after
     * poll creation
     */
    @get:JsonProperty("added_by_chat")
    @param:JsonProperty("added_by_chat")
    public val addedByChat: Chat? = null,
    /**
     * *Optional*. Point in time (Unix timestamp) when the option was added; omitted if the option
     * existed in the original poll
     */
    @get:JsonProperty("addition_date")
    @param:JsonProperty("addition_date")
    public val additionDate: Long? = null,
)
