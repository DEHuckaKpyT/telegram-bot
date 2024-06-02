package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long

/**
 * Created on 02.06.2024.
 *
 * This object represents a story.
 *
 * @see [Story] (https://core.telegram.org/bots/api/#story)
 *
 * @author KScript
 *
 * @param chat Chat that posted the story
 * @param id Unique identifier for the story in the chat
 */
public data class Story(
    /**
     * Chat that posted the story
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    public val chat: Chat,
    /**
     * Unique identifier for the story in the chat
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: Long,
)
