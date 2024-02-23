package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
public data class Poll(
    @param:JsonProperty("id") val id: String,
    @param:JsonProperty("question") val question: String,
    @param:JsonProperty("options") val options: List<PollOption>,
    @param:JsonProperty("total_voter_count") val totalVoterCount: Int,
    @param:JsonProperty("is_closed") val isClosed: Boolean,
    @param:JsonProperty("is_anonymous") val isAnonymous: Boolean,
    @param:JsonProperty("type") val type: String,
    @param:JsonProperty("allows_multiple_answers") val allowsMultipleAnswers: Boolean,
    @param:JsonProperty("correct_option_id") val correctOptionId: Int? = null,
    @param:JsonProperty("explanation") val explanation: String? = null,
    @param:JsonProperty("explanation_entities") val explanationEntities: List<MessageEntity>? = null,
    @param:JsonProperty("open_period") val openPeriod: Int? = null,
    @param:JsonProperty("close_date") val closeDate: Long? = null,
)

public data class PollOption(
    @get:JsonProperty("text") @param:JsonProperty("text") val text: String,
    @get:JsonProperty("voter_count") @param:JsonProperty("voter_count") val voterCount: Int,
)

public data class PollAnswer(
    @param:JsonProperty("poll_id") val pollId: String,
    @param:JsonProperty("voter_chat") val chat: Chat? = null,
    @param:JsonProperty("user") val user: User? = null,
    @param:JsonProperty("option_ids") val optionIds: List<Int>,
)