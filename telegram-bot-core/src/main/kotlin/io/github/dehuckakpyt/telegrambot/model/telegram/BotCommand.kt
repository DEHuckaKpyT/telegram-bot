package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * This object represents a bot command.
 *
 * @see [BotCommand] (https://core.telegram.org/bots/api/#botcommand)
 *
 * @author KScript
 *
 * @param command Text of the command; 1-32 characters. Can contain only lowercase English letters,
 * digits and underscores.
 * @param description Description of the command; 1-256 characters.
 */
public data class BotCommand(
    /**
     * Text of the command; 1-32 characters. Can contain only lowercase English letters, digits and
     * underscores.
     */
    @get:JsonProperty("command")
    @param:JsonProperty("command")
    public val command: String,
    /**
     * Description of the command; 1-256 characters.
     */
    @get:JsonProperty("description")
    @param:JsonProperty("description")
    public val description: String,
)
