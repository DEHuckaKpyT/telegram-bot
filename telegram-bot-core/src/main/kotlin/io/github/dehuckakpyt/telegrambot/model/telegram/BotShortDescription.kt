package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object represents the bot's short description.
 *
 * @see [BotShortDescription] (https://core.telegram.org/bots/api/#botshortdescription)
 *
 * @author KScript
 *
 * @param shortDescription The bot's short description
 */
public data class BotShortDescription(
    /**
     * The bot's short description
     */
    @get:JsonProperty("short_description")
    @param:JsonProperty("short_description")
    public val shortDescription: String,
)
