package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty

/**
 * Created on 03.06.2024.
 *
 * This object represents a boost added to a chat or changed.
 *
 * @see [ChatBoostUpdated] (https://core.telegram.org/bots/api/#chatboostupdated)
 *
 * @author KScript
 *
 * @param chat Chat which was boosted
 * @param boost Information about the chat boost
 */
public data class ChatBoostUpdated(
    /**
     * Chat which was boosted
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    public val chat: Chat,
    /**
     * Information about the chat boost
     */
    @get:JsonProperty("boost")
    @param:JsonProperty("boost")
    public val boost: ChatBoost,
)
