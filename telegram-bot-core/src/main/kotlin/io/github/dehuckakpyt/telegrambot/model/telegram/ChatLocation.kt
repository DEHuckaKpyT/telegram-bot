package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * Represents a location to which a chat is connected.
 *
 * @see [ChatLocation] (https://core.telegram.org/bots/api/#chatlocation)
 *
 * @author KScript
 *
 * @param location The location to which the supergroup is connected. Can't be a live location.
 * @param address Location address; 1-64 characters, as defined by the chat owner
 */
public data class ChatLocation(
    /**
     * The location to which the supergroup is connected. Can't be a live location.
     */
    @get:JsonProperty("location")
    @param:JsonProperty("location")
    public val location: Location,
    /**
     * Location address; 1-64 characters, as defined by the chat owner
     */
    @get:JsonProperty("address")
    @param:JsonProperty("address")
    public val address: String,
)
