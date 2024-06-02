package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * This object contains information about one answer option in a poll.
 *
 * @see [PollOption] (https://core.telegram.org/bots/api/#polloption)
 *
 * @author KScript
 *
 * @param text Option text, 1-100 characters
 * @param textEntities *Optional*. Special entities that appear in the option *text*. Currently,
 * only custom emoji entities are allowed in poll option texts
 * @param voterCount Number of users that voted for this option
 */
public data class PollOption(
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
     * Number of users that voted for this option
     */
    @get:JsonProperty("voter_count")
    @param:JsonProperty("voter_count")
    public val voterCount: Int,
)
