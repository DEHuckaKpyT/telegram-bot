package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * This object contains information about one answer option in a poll to send.
 *
 * @see [InputPollOption] (https://core.telegram.org/bots/api/#inputpolloption)
 *
 * @author KScript
 *
 * @param text Option text, 1-100 characters
 * @param textParseMode *Optional*. Mode for parsing entities in the text. See [formatting
 * options](https://core.telegram.org/bots/api/#formatting-options) for more details. Currently, only
 * custom emoji entities are allowed
 * @param textEntities *Optional*. A JSON-serialized list of special entities that appear in the
 * poll option text. It can be specified instead of *text_parse_mode*
 */
public data class InputPollOption(
    /**
     * Option text, 1-100 characters
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: String,
    /**
     * *Optional*. Mode for parsing entities in the text. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details. Currently,
     * only custom emoji entities are allowed
     */
    @get:JsonProperty("text_parse_mode")
    @param:JsonProperty("text_parse_mode")
    public val textParseMode: String? = null,
    /**
     * *Optional*. A JSON-serialized list of special entities that appear in the poll option text.
     * It can be specified instead of *text_parse_mode*
     */
    @get:JsonProperty("text_entities")
    @param:JsonProperty("text_entities")
    public val textEntities: List<MessageEntity>? = null,
)
