package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object represents the bot's name.
 *
 * @see [BotName] (https://core.telegram.org/bots/api/#botname)
 *
 * @author KScript
 *
 * @param name The bot's name
 */
public data class BotName(
    /**
     * The bot's name
     */
    @get:JsonProperty("name")
    @param:JsonProperty("name")
    public val name: String,
)
