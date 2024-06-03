package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long

/**
 * Created on 03.06.2024.
 *
 * This object represents a service message about a video chat scheduled in the chat.
 *
 * @see [VideoChatScheduled] (https://core.telegram.org/bots/api/#videochatscheduled)
 *
 * @author KScript
 *
 * @param startDate Point in time (Unix timestamp) when the video chat is supposed to be started by
 * a chat administrator
 */
public data class VideoChatScheduled(
    /**
     * Point in time (Unix timestamp) when the video chat is supposed to be started by a chat
     * administrator
     */
    @get:JsonProperty("start_date")
    @param:JsonProperty("start_date")
    public val startDate: Long,
)
