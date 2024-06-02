package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * This object contains information about a poll.
 *
 * @see [Poll] (https://core.telegram.org/bots/api/#poll)
 *
 * @author KScript
 *
 * @param id Unique poll identifier
 * @param question Poll question, 1-300 characters
 * @param questionEntities *Optional*. Special entities that appear in the *question*. Currently,
 * only custom emoji entities are allowed in poll questions
 * @param options List of poll options
 * @param totalVoterCount Total number of users that voted in the poll
 * @param isClosed *True*, if the poll is closed
 * @param isAnonymous *True*, if the poll is anonymous
 * @param type Poll type, currently can be “regular” or “quiz”
 * @param allowsMultipleAnswers *True*, if the poll allows multiple answers
 * @param correctOptionId *Optional*. 0-based identifier of the correct answer option. Available
 * only for polls in the quiz mode, which are closed, or was sent (not forwarded) by the bot or to the
 * private chat with the bot.
 * @param explanation *Optional*. Text that is shown when a user chooses an incorrect answer or taps
 * on the lamp icon in a quiz-style poll, 0-200 characters
 * @param explanationEntities *Optional*. Special entities like usernames, URLs, bot commands, etc.
 * that appear in the *explanation*
 * @param openPeriod *Optional*. Amount of time in seconds the poll will be active after creation
 * @param closeDate *Optional*. Point in time (Unix timestamp) when the poll will be automatically
 * closed
 */
public data class Poll(
    /**
     * Unique poll identifier
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: String,
    /**
     * Poll question, 1-300 characters
     */
    @get:JsonProperty("question")
    @param:JsonProperty("question")
    public val question: String,
    /**
     * *Optional*. Special entities that appear in the *question*. Currently, only custom emoji
     * entities are allowed in poll questions
     */
    @get:JsonProperty("question_entities")
    @param:JsonProperty("question_entities")
    public val questionEntities: List<MessageEntity>? = null,
    /**
     * List of poll options
     */
    @get:JsonProperty("options")
    @param:JsonProperty("options")
    public val options: List<PollOption>,
    /**
     * Total number of users that voted in the poll
     */
    @get:JsonProperty("total_voter_count")
    @param:JsonProperty("total_voter_count")
    public val totalVoterCount: Int,
    /**
     * *True*, if the poll is closed
     */
    @get:JsonProperty("is_closed")
    @param:JsonProperty("is_closed")
    public val isClosed: Boolean,
    /**
     * *True*, if the poll is anonymous
     */
    @get:JsonProperty("is_anonymous")
    @param:JsonProperty("is_anonymous")
    public val isAnonymous: Boolean,
    /**
     * Poll type, currently can be “regular” or “quiz”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    public val type: String,
    /**
     * *True*, if the poll allows multiple answers
     */
    @get:JsonProperty("allows_multiple_answers")
    @param:JsonProperty("allows_multiple_answers")
    public val allowsMultipleAnswers: Boolean,
    /**
     * *Optional*. 0-based identifier of the correct answer option. Available only for polls in the
     * quiz mode, which are closed, or was sent (not forwarded) by the bot or to the private chat with
     * the bot.
     */
    @get:JsonProperty("correct_option_id")
    @param:JsonProperty("correct_option_id")
    public val correctOptionId: Long? = null,
    /**
     * *Optional*. Text that is shown when a user chooses an incorrect answer or taps on the lamp
     * icon in a quiz-style poll, 0-200 characters
     */
    @get:JsonProperty("explanation")
    @param:JsonProperty("explanation")
    public val explanation: String? = null,
    /**
     * *Optional*. Special entities like usernames, URLs, bot commands, etc. that appear in the
     * *explanation*
     */
    @get:JsonProperty("explanation_entities")
    @param:JsonProperty("explanation_entities")
    public val explanationEntities: List<MessageEntity>? = null,
    /**
     * *Optional*. Amount of time in seconds the poll will be active after creation
     */
    @get:JsonProperty("open_period")
    @param:JsonProperty("open_period")
    public val openPeriod: Int? = null,
    /**
     * *Optional*. Point in time (Unix timestamp) when the poll will be automatically closed
     */
    @get:JsonProperty("close_date")
    @param:JsonProperty("close_date")
    public val closeDate: Long? = null,
)
