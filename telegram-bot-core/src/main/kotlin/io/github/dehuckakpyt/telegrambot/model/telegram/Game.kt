package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * This object represents a game. Use BotFather to create and edit games, their short names will act
 * as unique identifiers.
 *
 * @see [Game] (https://core.telegram.org/bots/api/#game)
 *
 * @author KScript
 *
 * @param title Title of the game
 * @param description Description of the game
 * @param photo Photo that will be displayed in the game message in chats.
 * @param text *Optional*. Brief description of the game or high scores included in the game
 * message. Can be automatically edited to include current high scores for the game when the bot calls
 * [setGameScore](https://core.telegram.org/bots/api/#setgamescore), or manually edited using
 * [editMessageText](https://core.telegram.org/bots/api/#editmessagetext). 0-4096 characters.
 * @param textEntities *Optional*. Special entities that appear in *text*, such as usernames, URLs,
 * bot commands, etc.
 * @param animation *Optional*. Animation that will be displayed in the game message in chats.
 * Upload via [BotFather](https://t.me/botfather)
 */
public data class Game(
    /**
     * Title of the game
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String,
    /**
     * Description of the game
     */
    @get:JsonProperty("description")
    @param:JsonProperty("description")
    public val description: String,
    /**
     * Photo that will be displayed in the game message in chats.
     */
    @get:JsonProperty("photo")
    @param:JsonProperty("photo")
    public val photo: List<PhotoSize>,
    /**
     * *Optional*. Brief description of the game or high scores included in the game message. Can be
     * automatically edited to include current high scores for the game when the bot calls
     * [setGameScore](https://core.telegram.org/bots/api/#setgamescore), or manually edited using
     * [editMessageText](https://core.telegram.org/bots/api/#editmessagetext). 0-4096 characters.
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: String? = null,
    /**
     * *Optional*. Special entities that appear in *text*, such as usernames, URLs, bot commands,
     * etc.
     */
    @get:JsonProperty("text_entities")
    @param:JsonProperty("text_entities")
    public val textEntities: List<MessageEntity>? = null,
    /**
     * *Optional*. Animation that will be displayed in the game message in chats. Upload via
     * [BotFather](https://t.me/botfather)
     */
    @get:JsonProperty("animation")
    @param:JsonProperty("animation")
    public val animation: Animation? = null,
)
