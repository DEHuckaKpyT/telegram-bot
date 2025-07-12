package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List

/**
 * Describes a checklist to create.
 *
 * @see [InputChecklist] (https://core.telegram.org/bots/api/#inputchecklist)
 *
 * @author KScript
 *
 * @param title Title of the checklist; 1-255 characters after entities parsing
 * @param parseMode Optional. Mode for parsing entities in the title. See [formatting
 * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
 * @param titleEntities *Optional*. List of special entities that appear in the title, which can be
 * specified instead of parse_mode. Currently, only *bold*, *italic*, *underline*, *strikethrough*,
 * *spoiler*, and *custom_emoji* entities are allowed.
 * @param tasks List of 1-30 tasks in the checklist
 * @param othersCanAddTasks *Optional*. Pass *True* if other users can add tasks to the checklist
 * @param othersCanMarkTasksAsDone *Optional*. Pass *True* if other users can mark tasks as done or
 * not done in the checklist
 */
public data class InputChecklist(
    /**
     * Title of the checklist; 1-255 characters after entities parsing
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String,
    /**
     * Optional. Mode for parsing entities in the title. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     */
    @get:JsonProperty("parse_mode")
    @param:JsonProperty("parse_mode")
    public val parseMode: String? = null,
    /**
     * *Optional*. List of special entities that appear in the title, which can be specified instead
     * of parse_mode. Currently, only *bold*, *italic*, *underline*, *strikethrough*, *spoiler*, and
     * *custom_emoji* entities are allowed.
     */
    @get:JsonProperty("title_entities")
    @param:JsonProperty("title_entities")
    public val titleEntities: List<MessageEntity>? = null,
    /**
     * List of 1-30 tasks in the checklist
     */
    @get:JsonProperty("tasks")
    @param:JsonProperty("tasks")
    public val tasks: List<InputChecklistTask>,
    /**
     * *Optional*. Pass *True* if other users can add tasks to the checklist
     */
    @get:JsonProperty("others_can_add_tasks")
    @param:JsonProperty("others_can_add_tasks")
    public val othersCanAddTasks: Boolean? = null,
    /**
     * *Optional*. Pass *True* if other users can mark tasks as done or not done in the checklist
     */
    @get:JsonProperty("others_can_mark_tasks_as_done")
    @param:JsonProperty("others_can_mark_tasks_as_done")
    public val othersCanMarkTasksAsDone: Boolean? = null,
)
