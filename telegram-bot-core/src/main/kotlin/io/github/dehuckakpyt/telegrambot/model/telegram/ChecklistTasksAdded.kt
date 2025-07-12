package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.collections.List

/**
 * Describes a service message about tasks added to a checklist.
 *
 * @see [ChecklistTasksAdded] (https://core.telegram.org/bots/api/#checklisttasksadded)
 *
 * @author KScript
 *
 * @param checklistMessage *Optional*. Message containing the checklist to which the tasks were
 * added. Note that the Message object in this field will not contain the *reply_to_message* field even
 * if it itself is a reply.
 * @param tasks List of tasks added to the checklist
 */
public data class ChecklistTasksAdded(
    /**
     * *Optional*. Message containing the checklist to which the tasks were added. Note that the
     * Message object in this field will not contain the *reply_to_message* field even if it itself is
     * a reply.
     */
    @get:JsonProperty("checklist_message")
    @param:JsonProperty("checklist_message")
    public val checklistMessage: Message? = null,
    /**
     * List of tasks added to the checklist
     */
    @get:JsonProperty("tasks")
    @param:JsonProperty("tasks")
    public val tasks: List<ChecklistTask>,
)
