package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty

/**
 * This object represents a chat background.
 *
 * @see [ChatBackground] (https://core.telegram.org/bots/api/#chatbackground)
 *
 * @author KScript
 *
 * @param type Type of the background
 */
public data class ChatBackground(
    /**
     * Type of the background
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    public val type: BackgroundType,
)
