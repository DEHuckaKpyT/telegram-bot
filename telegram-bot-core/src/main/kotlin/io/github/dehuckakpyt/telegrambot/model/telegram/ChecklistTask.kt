package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * Describes a task in a checklist.
 *
 * @see [ChecklistTask] (https://core.telegram.org/bots/api/#checklisttask)
 *
 * @author KScript
 *
 * @param id Unique identifier of the task
 * @param text Text of the task
 * @param textEntities *Optional*. Special entities that appear in the task text
 * @param completedByUser *Optional*. User that completed the task; omitted if the task wasn't
 * completed
 * @param completionDate *Optional*. Point in time (Unix timestamp) when the task was completed; 0
 * if the task wasn't completed
 */
public data class ChecklistTask(
    /**
     * Unique identifier of the task
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: Long,
    /**
     * Text of the task
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: String,
    /**
     * *Optional*. Special entities that appear in the task text
     */
    @get:JsonProperty("text_entities")
    @param:JsonProperty("text_entities")
    public val textEntities: List<MessageEntity>? = null,
    /**
     * *Optional*. User that completed the task; omitted if the task wasn't completed
     */
    @get:JsonProperty("completed_by_user")
    @param:JsonProperty("completed_by_user")
    public val completedByUser: User? = null,
    /**
     * *Optional*. Point in time (Unix timestamp) when the task was completed; 0 if the task wasn't
     * completed
     */
    @get:JsonProperty("completion_date")
    @param:JsonProperty("completion_date")
    public val completionDate: Long? = null,
)
