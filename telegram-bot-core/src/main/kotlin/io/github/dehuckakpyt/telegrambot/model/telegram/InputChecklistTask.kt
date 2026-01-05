package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * Describes a task to add to a checklist.
 *
 * @see [InputChecklistTask] (https://core.telegram.org/bots/api/#inputchecklisttask)
 *
 * @author KScript
 *
 * @param id Unique identifier of the task; must be positive and unique among all task identifiers
 * currently present in the checklist
 * @param text Text of the task; 1-100 characters after entities parsing
 * @param parseMode *Optional*. Mode for parsing entities in the text. See [formatting
 * options](https://core.telegram.org/bots/api#formatting-options) for more details.
 * @param textEntities *Optional*. List of special entities that appear in the text, which can be
 * specified instead of parse_mode. Currently, only *bold*, *italic*, *underline*, *strikethrough*,
 * *spoiler*, and *custom_emoji* entities are allowed.
 */
public data class InputChecklistTask(
    /**
     * Unique identifier of the task; must be positive and unique among all task identifiers
     * currently present in the checklist
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: Long,
    /**
     * Text of the task; 1-100 characters after entities parsing
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: String,
    /**
     * *Optional*. Mode for parsing entities in the text. See [formatting
     * options](https://core.telegram.org/bots/api#formatting-options) for more details.
     */
    @get:JsonProperty("parse_mode")
    @param:JsonProperty("parse_mode")
    public val parseMode: String? = null,
    /**
     * *Optional*. List of special entities that appear in the text, which can be specified instead
     * of parse_mode. Currently, only *bold*, *italic*, *underline*, *strikethrough*, *spoiler*, and
     * *custom_emoji* entities are allowed.
     */
    @get:JsonProperty("text_entities")
    @param:JsonProperty("text_entities")
    public val textEntities: List<MessageEntity>? = null,
)
