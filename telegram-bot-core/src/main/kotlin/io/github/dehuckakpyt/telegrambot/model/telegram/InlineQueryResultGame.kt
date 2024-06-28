package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Represents a [Game](https://core.telegram.org/bots/api/#games).
 *
 * @see [InlineQueryResultGame] (https://core.telegram.org/bots/api/#inlinequeryresultgame)
 *
 * @author KScript
 *
 * @param id Unique identifier for this result, 1-64 bytes
 * @param gameShortName Short name of the game
 * @param replyMarkup *Optional*. [Inline
 * keyboard](https://core.telegram.org/bots/features#inline-keyboards) attached to the message
 */
public data class InlineQueryResultGame(
    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    override val id: String,
    /**
     * Short name of the game
     */
    @get:JsonProperty("game_short_name")
    @param:JsonProperty("game_short_name")
    public val gameShortName: String,
    /**
     * *Optional*. [Inline keyboard](https://core.telegram.org/bots/features#inline-keyboards)
     * attached to the message
     */
    @get:JsonProperty("reply_markup")
    @param:JsonProperty("reply_markup")
    override val replyMarkup: InlineKeyboardMarkup? = null,
) : InlineQueryResult {
    @get:JsonProperty("type")
    override val type: String = "game"
}
