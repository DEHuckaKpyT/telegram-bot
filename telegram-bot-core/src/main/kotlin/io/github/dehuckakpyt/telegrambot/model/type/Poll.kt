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
    @get:JsonProperty("id") @param:JsonProperty("id") val id: String,
    @get:JsonProperty("question") @param:JsonProperty("question") val question: String,
    @get:JsonProperty("options") @param:JsonProperty("options") val options: List<PollOption>,
    @get:JsonProperty("total_voter_count") @param:JsonProperty("total_voter_count") val totalVoterCount: Int,
    @get:JsonProperty("is_closed") @param:JsonProperty("is_closed") val isClosed: Boolean,
    @get:JsonProperty("is_anonymous") @param:JsonProperty("is_anonymous") val isAnonymous: Boolean,
    @get:JsonProperty("type") @param:JsonProperty("type") val type: String,
    @get:JsonProperty("allows_multiple_answers") @param:JsonProperty("allows_multiple_answers") val allowsMultipleAnswers: Boolean,
    @get:JsonProperty("correct_option_id") @param:JsonProperty("correct_option_id") val correctOptionId: Int? = null,
    @get:JsonProperty("explanation") @param:JsonProperty("explanation") val explanation: String? = null,
    @get:JsonProperty("explanation_entities") @param:JsonProperty("explanation_entities") val explanationEntities: List<MessageEntity>? = null,
    @get:JsonProperty("open_period") @param:JsonProperty("open_period") val openPeriod: Int? = null,
    @get:JsonProperty("close_date") @param:JsonProperty("close_date") val closeDate: Long? = null,
)

public data class PollOption(
    @get:JsonProperty("text") @param:JsonProperty("text") val text: String,
    @get:JsonProperty("voter_count") @param:JsonProperty("voter_count") val voterCount: Int,
)

public data class PollAnswer(
    @get:JsonProperty("poll_id") @param:JsonProperty("poll_id") val pollId: String,
    @get:JsonProperty("user") @param:JsonProperty("user") val user: User,
    @get:JsonProperty("option_ids") @param:JsonProperty("option_ids") val optionIds: List<Int>,
)