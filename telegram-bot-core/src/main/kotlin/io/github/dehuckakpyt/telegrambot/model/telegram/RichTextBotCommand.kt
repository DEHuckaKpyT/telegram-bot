package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A bot command.
 *
 * @see [RichTextBotCommand] (https://core.telegram.org/bots/api/#richtextbotcommand)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “bot_command”
 * @param text The text
 * @param botCommand The bot command
 */
public data class RichTextBotCommand(
    /**
     * Type of the rich text, always “bot_command”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The text
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: RichText,
    /**
     * The bot command
     */
    @get:JsonProperty("bot_command")
    @param:JsonProperty("bot_command")
    public val botCommand: String,
) : RichText
