package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * This object represents a service message about a video chat ended in the chat.
 *
 * @see [VideoChatEnded] (https://core.telegram.org/bots/api/#videochatended)
 *
 * @author KScript
 *
 * @param duration Video chat duration in seconds
 */
public data class VideoChatEnded(
    /**
     * Video chat duration in seconds
     */
    @get:JsonProperty("duration")
    @param:JsonProperty("duration")
    public val duration: Int,
)
