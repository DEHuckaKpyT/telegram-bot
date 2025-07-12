package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List

/**
 * Describes a checklist.
 *
 * @see [Checklist] (https://core.telegram.org/bots/api/#checklist)
 *
 * @author KScript
 *
 * @param title Title of the checklist
 * @param titleEntities *Optional*. Special entities that appear in the checklist title
 * @param tasks List of tasks in the checklist
 * @param othersCanAddTasks *Optional*. *True*, if users other than the creator of the list can add
 * tasks to the list
 * @param othersCanMarkTasksAsDone *Optional*. *True*, if users other than the creator of the list
 * can mark tasks as done or not done
 */
public data class Checklist(
    /**
     * Title of the checklist
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String,
    /**
     * *Optional*. Special entities that appear in the checklist title
     */
    @get:JsonProperty("title_entities")
    @param:JsonProperty("title_entities")
    public val titleEntities: List<MessageEntity>? = null,
    /**
     * List of tasks in the checklist
     */
    @get:JsonProperty("tasks")
    @param:JsonProperty("tasks")
    public val tasks: List<ChecklistTask>,
    /**
     * *Optional*. *True*, if users other than the creator of the list can add tasks to the list
     */
    @get:JsonProperty("others_can_add_tasks")
    @param:JsonProperty("others_can_add_tasks")
    public val othersCanAddTasks: Boolean? = null,
    /**
     * *Optional*. *True*, if users other than the creator of the list can mark tasks as done or not
     * done
     */
    @get:JsonProperty("others_can_mark_tasks_as_done")
    @param:JsonProperty("others_can_mark_tasks_as_done")
    public val othersCanMarkTasksAsDone: Boolean? = null,
)
