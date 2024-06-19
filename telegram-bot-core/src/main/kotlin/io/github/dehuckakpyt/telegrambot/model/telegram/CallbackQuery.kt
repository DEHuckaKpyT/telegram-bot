package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * This object represents an incoming callback query from a callback button in an [inline
 * keyboard](https://core.telegram.org/bots/features#inline-keyboards). If the button that originated
 * the query was attached to a message sent by the bot, the field *message* will be present. If the
 * button was attached to a message sent via the bot (in [inline
 * mode](https://core.telegram.org/bots/api/#inline-mode)), the field *inline_message_id* will be
 * present. Exactly one of the fields *data* or *game_short_name* will be present.
 *
 * @see [CallbackQuery] (https://core.telegram.org/bots/api/#callbackquery)
 *
 * @author KScript
 *
 * @param id Unique identifier for this query
 * @param from Sender
 * @param message *Optional*. Message sent by the bot with the callback button that originated the
 * query
 * @param inlineMessageId *Optional*. Identifier of the message sent via the bot in inline mode,
 * that originated the query.
 * @param chatInstance Global identifier, uniquely corresponding to the chat to which the message
 * with the callback button was sent. Useful for high scores in
 * [games](https://core.telegram.org/bots/api/#games).
 * @param data *Optional*. Data associated with the callback button. Be aware that the message
 * originated the query can contain no callback buttons with this data.
 * @param gameShortName *Optional*. Short name of a
 * [Game](https://core.telegram.org/bots/api/#games) to be returned, serves as the unique identifier
 * for the game
 */
public data class CallbackQuery(
    /**
     * Unique identifier for this query
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: String,
    /**
     * Sender
     */
    @get:JsonProperty("from")
    @param:JsonProperty("from")
    public val from: User,
    /**
     * *Optional*. Message sent by the bot with the callback button that originated the query
     */
    @get:JsonProperty("message")
    @param:JsonProperty("message")
    public val message: MaybeInaccessibleMessage? = null,
    /**
     * *Optional*. Identifier of the message sent via the bot in inline mode, that originated the
     * query.
     */
    @get:JsonProperty("inline_message_id")
    @param:JsonProperty("inline_message_id")
    public val inlineMessageId: String? = null,
    /**
     * Global identifier, uniquely corresponding to the chat to which the message with the callback
     * button was sent. Useful for high scores in [games](https://core.telegram.org/bots/api/#games).
     */
    @get:JsonProperty("chat_instance")
    @param:JsonProperty("chat_instance")
    public val chatInstance: String,
    /**
     * *Optional*. Data associated with the callback button. Be aware that the message originated
     * the query can contain no callback buttons with this data.
     */
    @get:JsonProperty("data")
    @param:JsonProperty("data")
    public val `data`: String? = null,
    /**
     * *Optional*. Short name of a [Game](https://core.telegram.org/bots/api/#games) to be returned,
     * serves as the unique identifier for the game
     */
    @get:JsonProperty("game_short_name")
    @param:JsonProperty("game_short_name")
    public val gameShortName: String? = null,
)
