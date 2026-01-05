package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * This object describes the rating of a user based on their Telegram Star spendings.
 *
 * @see [UserRating] (https://core.telegram.org/bots/api/#userrating)
 *
 * @author KScript
 *
 * @param level Current level of the user, indicating their reliability when purchasing digital
 * goods and services. A higher level suggests a more trustworthy customer; a negative level is likely
 * reason for concern.
 * @param rating Numerical value of the user's rating; the higher the rating, the better
 * @param currentLevelRating The rating value required to get the current level
 * @param nextLevelRating *Optional*. The rating value required to get to the next level; omitted if
 * the maximum level was reached
 */
public data class UserRating(
    /**
     * Current level of the user, indicating their reliability when purchasing digital goods and
     * services. A higher level suggests a more trustworthy customer; a negative level is likely reason
     * for concern.
     */
    @get:JsonProperty("level")
    @param:JsonProperty("level")
    public val level: Int,
    /**
     * Numerical value of the user's rating; the higher the rating, the better
     */
    @get:JsonProperty("rating")
    @param:JsonProperty("rating")
    public val rating: Int,
    /**
     * The rating value required to get the current level
     */
    @get:JsonProperty("current_level_rating")
    @param:JsonProperty("current_level_rating")
    public val currentLevelRating: Int,
    /**
     * *Optional*. The rating value required to get to the next level; omitted if the maximum level
     * was reached
     */
    @get:JsonProperty("next_level_rating")
    @param:JsonProperty("next_level_rating")
    public val nextLevelRating: Int? = null,
)
