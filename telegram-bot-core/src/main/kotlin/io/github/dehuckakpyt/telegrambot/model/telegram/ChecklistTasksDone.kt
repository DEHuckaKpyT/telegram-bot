package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.collections.List

/**
 * Describes a service message about checklist tasks marked as done or not done.
 *
 * @see [ChecklistTasksDone] (https://core.telegram.org/bots/api/#checklisttasksdone)
 *
 * @author KScript
 *
 * @param checklistMessage *Optional*. Message containing the checklist whose tasks were marked as
 * done or not done. Note that the Message object in this field will not contain the *reply_to_message*
 * field even if it itself is a reply.
 * @param markedAsDoneTaskIds *Optional*. Identifiers of the tasks that were marked as done
 * @param markedAsNotDoneTaskIds *Optional*. Identifiers of the tasks that were marked as not done
 */
public data class ChecklistTasksDone(
    /**
     * *Optional*. Message containing the checklist whose tasks were marked as done or not done.
     * Note that the Message object in this field will not contain the *reply_to_message* field even if
     * it itself is a reply.
     */
    @get:JsonProperty("checklist_message")
    @param:JsonProperty("checklist_message")
    public val checklistMessage: Message? = null,
    /**
     * *Optional*. Identifiers of the tasks that were marked as done
     */
    @get:JsonProperty("marked_as_done_task_ids")
    @param:JsonProperty("marked_as_done_task_ids")
    public val markedAsDoneTaskIds: List<Long>? = null,
    /**
     * *Optional*. Identifiers of the tasks that were marked as not done
     */
    @get:JsonProperty("marked_as_not_done_task_ids")
    @param:JsonProperty("marked_as_not_done_task_ids")
    public val markedAsNotDoneTaskIds: List<Long>? = null,
)
