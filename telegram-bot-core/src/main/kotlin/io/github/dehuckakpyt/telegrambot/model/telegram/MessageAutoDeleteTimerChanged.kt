package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * This object represents a service message about a change in auto-delete timer settings.
 *
 * @see [MessageAutoDeleteTimerChanged]
 * (https://core.telegram.org/bots/api/#messageautodeletetimerchanged)
 *
 * @author KScript
 *
 * @param messageAutoDeleteTime New auto-delete time for messages in the chat; in seconds
 */
public data class MessageAutoDeleteTimerChanged(
    /**
     * New auto-delete time for messages in the chat; in seconds
     */
    @get:JsonProperty("message_auto_delete_time")
    @param:JsonProperty("message_auto_delete_time")
    public val messageAutoDeleteTime: Int,
)
