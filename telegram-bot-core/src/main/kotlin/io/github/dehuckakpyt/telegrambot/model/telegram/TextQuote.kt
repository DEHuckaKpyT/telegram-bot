package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * This object contains information about the quoted part of a message that is replied to by the
 * given message.
 *
 * @see [TextQuote] (https://core.telegram.org/bots/api/#textquote)
 *
 * @author KScript
 *
 * @param text Text of the quoted part of a message that is replied to by the given message
 * @param entities *Optional*. Special entities that appear in the quote. Currently, only *bold*,
 * *italic*, *underline*, *strikethrough*, *spoiler*, and *custom_emoji* entities are kept in quotes.
 * @param position Approximate quote position in the original message in UTF-16 code units as
 * specified by the sender
 * @param isManual *Optional*. *True*, if the quote was chosen manually by the message sender.
 * Otherwise, the quote was added automatically by the server.
 */
public data class TextQuote(
    /**
     * Text of the quoted part of a message that is replied to by the given message
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: String,
    /**
     * *Optional*. Special entities that appear in the quote. Currently, only *bold*, *italic*,
     * *underline*, *strikethrough*, *spoiler*, and *custom_emoji* entities are kept in quotes.
     */
    @get:JsonProperty("entities")
    @param:JsonProperty("entities")
    public val entities: List<MessageEntity>? = null,
    /**
     * Approximate quote position in the original message in UTF-16 code units as specified by the
     * sender
     */
    @get:JsonProperty("position")
    @param:JsonProperty("position")
    public val position: Int,
    /**
     * *Optional*. *True*, if the quote was chosen manually by the message sender. Otherwise, the
     * quote was added automatically by the server.
     */
    @get:JsonProperty("is_manual")
    @param:JsonProperty("is_manual")
    public val isManual: Boolean? = null,
)
