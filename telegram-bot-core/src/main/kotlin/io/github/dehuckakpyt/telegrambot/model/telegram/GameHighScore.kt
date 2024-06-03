package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * Created on 03.06.2024.
 *
 * This object represents one row of the high scores table for a game.
 *
 * @see [GameHighScore] (https://core.telegram.org/bots/api/#gamehighscore)
 *
 * @author KScript
 *
 * @param position Position in high score table for the game
 * @param user User
 * @param score Score
 */
public data class GameHighScore(
    /**
     * Position in high score table for the game
     */
    @get:JsonProperty("position")
    @param:JsonProperty("position")
    public val position: Int,
    /**
     * User
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    public val user: User,
    /**
     * Score
     */
    @get:JsonProperty("score")
    @param:JsonProperty("score")
    public val score: Int,
)
