package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * Created on 03.06.2024.
 *
 * This object represents an answer of a user in a non-anonymous poll.
 *
 * @see [PollAnswer] (https://core.telegram.org/bots/api/#pollanswer)
 *
 * @author KScript
 *
 * @param pollId Unique poll identifier
 * @param voterChat *Optional*. The chat that changed the answer to the poll, if the voter is
 * anonymous
 * @param user *Optional*. The user that changed the answer to the poll, if the voter isn't
 * anonymous
 * @param optionIds 0-based identifiers of chosen answer options. May be empty if the vote was
 * retracted.
 */
public data class PollAnswer(
    /**
     * Unique poll identifier
     */
    @get:JsonProperty("poll_id")
    @param:JsonProperty("poll_id")
    public val pollId: String,
    /**
     * *Optional*. The chat that changed the answer to the poll, if the voter is anonymous
     */
    @get:JsonProperty("voter_chat")
    @param:JsonProperty("voter_chat")
    public val voterChat: Chat? = null,
    /**
     * *Optional*. The user that changed the answer to the poll, if the voter isn't anonymous
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    public val user: User? = null,
    /**
     * 0-based identifiers of chosen answer options. May be empty if the vote was retracted.
     */
    @get:JsonProperty("option_ids")
    @param:JsonProperty("option_ids")
    public val optionIds: List<Long>,
)
